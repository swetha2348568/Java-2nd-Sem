import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class OrderProcessingException extends Exception{
    public OrderProcessingException(String message){
        super(message);
    }

}


class InsufficientInventoryException extends OrderProcessingException{

    public InsufficientInventoryException(String message){
        super(message);
    }
}



class OrderCancelledException extends OrderProcessingException{

    public OrderCancelledException(String message){
        super(message);
    }
}



enum OrderStatus{
    PROCESSING, COMPLETED, CANCELLED
}



class Item{
    public String name;
    public int quantity;

    // Constructor
    public Item(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    public int getQuantity(){
        return quantity;
    }
}



class Order{
    public int orderId;
    public List<Item> item;
    public OrderStatus status;

    // Constructor
    public Order(int orderId, List<Item> item){
        this.orderId = orderId;
        this.item = item;
        this.status = OrderStatus.COMPLETED;
    }

    public int getOrderId(){
        return orderId;
    }

    public List<Item> getItems(){
        return item;
    }

    public OrderStatus getStatus() {

        return status;
    }

    public void setStatus(OrderStatus status){
        this.status = status;
    }

}



public class EnhancedOrderFullfilmentSystem {

    private List<Order> orders;
    private Map<String,Integer> inventory; // Map provides fast access to inventory item using keys
    private final Object lock = new Object(); // This object is used for synchronization purposes

    public EnhancedOrderFullfilmentSystem(){
        orders = new ArrayList<>();
        inventory = new HashMap<>();
    }

    // The usage of synchronized is it will allow single thread at a time

    public synchronized void placeOrder(Order order){
        orders.add(order);
        System.out.println("Order " + order.getOrderId() + " placed");
    }

    //  to update the inventory

    public synchronized void updateInventory(Order order) throws InsufficientInventoryException{

        for(Item item: order.getItems()){
            String itemName = item.getName();
            int itemQuantity = item.getQuantity();

            if(inventory.containsKey(itemName)){
                int currentQuantity = inventory.get(itemName);
                if(currentQuantity >= itemQuantity){
                    inventory.put(itemName, currentQuantity - itemQuantity);
                }
                else{
                    throw new InsufficientInventoryException("Insufficient inventory for item: " + itemName);
                }
            }
            else{
                throw new InsufficientInventoryException("Item " + itemName + " not found in the inventory");
            }
        }
    }

    //  check the inventory availability

    public synchronized boolean checkInventoryAvailability(Item item){

        int availableQuantity = inventory.getOrDefault(item.getName(), 0);
        return availableQuantity >= item.getQuantity();

    }


    public void startProcessing(){

        ExecutorService executor = Executors.newFixedThreadPool(3); // Creates a thread pool with three worker threads
        // Looping through the list of orders
        for(Order order: orders){
            executor.execute(()->{ // submits each order processing task
                try{
                    synchronized(lock){
                        updateInventory(order); // Updates the inventory based on the items in the order
                    }
                    order.setStatus(OrderStatus.COMPLETED); // Sets the order status as completed if the order is sucessfully
                    System.out.println("Order " + order.getOrderId() + " processed");
                }
                catch(InsufficientInventoryException e){

                    order.setStatus(OrderStatus.CANCELLED); // Sets the order status as cancelled if the inventory is insufficient
                    System.out.println("Order " + order.getOrderId() + " cancelled " + e.getMessage());

                }
            });
        }
        executor.shutdown();
        while (!executor.isTerminated()){

        }
    }

    public void waitForCompletion(){
        startProcessing();
    }

    public synchronized OrderStatus trackOrderStatus(int orderId){
        for(Order order:orders){
            if(order.getOrderId() == orderId){
                return order.getStatus();
            }
        }
        return null;

    }

    // Main Method

    public static void main(String[] args) {

        EnhancedOrderFullfilmentSystem eos = new EnhancedOrderFullfilmentSystem();

        // Adding items to the inventory

        eos.inventory.put("Item_1", 5);
        eos.inventory.put("Item_2", 15);
        eos.inventory.put("Item_3", 2);

        // Creating orders

        List<Item> items1 = List.of(new Item("Item_1", 4), new Item("Item_2", 12));
        List<Item> items2 = List.of(new Item("Item_3", 30));

        Order order1 = new Order(2, items1);
        Order order2 = new Order(3, items2);

        System.out.println("");
        System.out.println("Enhanced Order Fulfillment System");
        System.out.println("---------------------------------");
        System.out.println("");

        // Placing the order

        eos.placeOrder(order1);
        eos.placeOrder(order2);

        // Start order processing

        eos.waitForCompletion();

        // Tracking Order Status

        System.out.println(eos.trackOrderStatus(1));
        System.out.println(eos.trackOrderStatus(2));
        System.out.println(" ");
    }

}

