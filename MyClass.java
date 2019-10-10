
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class MyClass {
  ArrayList <User> user = new ArrayList<>();
  ArrayList <Product> product = new ArrayList<>();
  User currentUser = null;

  void createUser(){
    //creating user here
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Scanner sc = new Scanner(System.in);
    String name,password,address,userType;
    Date dob = null;
    System.out.println("Enter User Name ");
    name = sc.nextLine();
    System.out.println("Enter Password");
    password = sc.nextLine();
    System.out.println("Enter Address");
    address = sc.nextLine();
    System.out.println("Enter DOB (DD-MM-YYYY)");
    try{
      dob = sdf.parse(sc.nextLine());
    }catch(Exception e){
      System.out.println("Wrong dob format");
    }
    System.out.println("User type (user or admin)");
    userType = sc.nextLine();
    User.Type type = User.Type.valueOf(userType.toUpperCase());
    Integer userId = user.size()+1;
    User user = new User(name,password,userId,address,dob,type);
    this.user.add(user);

  }

  boolean doLogin(){
    // doing login here
    String name,password;
    User user = null;

    if(this.user.size()==0){
      System.out.println("No user exist till now create one");
      return false;
    }
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter name");
    name = sc.nextLine();
    System.out.println("Enter password");
    password = sc.nextLine();

    Iterator<User> iter = this.user.iterator();
    while (iter.hasNext()) {
        User nextUser = iter.next();
        if(nextUser.doLogin(name,password)){
          user = nextUser;
          this.currentUser = user;
          break;
        }
    }

    return true;
  }

  void userViewAddProduct(){
    //user can view and add product here
    Scanner sc = new Scanner(System.in);
    System.out.println("Product List :");
    Iterator<Product> iter;
    Product curProduct;
    displayAllProduct();
    System.out.println("Enter product Id to add to cart");
    Integer productId = sc.nextInt();
    iter = this.product.iterator();
    while(iter.hasNext()){
      Product prod = iter.next();
      if(prod.isMatch(productId)){
        currentUser.addProduct(prod);
        System.out.println("Product with id :"+productId+" Added succesfully");
      }
    }
  }

  void userOption(){
    //user panel to do operatins
    Integer option;
    do{
      System.out.println("\nUser option :");
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter choice \n1. View and add Product\n2. View Cart and checkout\n3. View All Product \n4. Logout");
      option = sc.nextInt();
      if(option == 1){
        userViewAddProduct();
      }else if(option ==2){
        viewCartAndCheckout();
      }else if(option ==3){
        displayAllProduct();
      }else if(option == 4){
        this.currentUser = null;
        System.out.println("Logged out");
      }
    }while(option!=4);

  }

  void displayAllProduct(){
    //to display all product
    Iterator<Product> iter = this.product.iterator();
    while(iter.hasNext()){
      Product product = iter.next();
      System.out.println(product.getProduct());
    }
  }
  void viewCartAndCheckout(){
    //Display total amount and product list in cart
    Integer amount = this.currentUser.getTotalPayable();
    System.out.print("Total Amount ::"+amount+"\n");
    Iterator<Product> iter = this.currentUser.getProduct().iterator();
    while(iter.hasNext()){
      Product prod = iter.next();
      System.out.println(prod.getProduct());
    }
  }

  void adminOption(){
    //displaying admin option here
    Integer option;
    do{
      Scanner sc = new Scanner(System.in);
      System.out.println("admin option :");
      System.out.println("1.Add product\n2.Update Product\n3.Delete Prodcut\n4.View All product \n5. Logout");
      option = sc.nextInt();
      if(option == 1){
        addProduct();
      }else if(option == 2){
        updateProduct();
      }else if(option == 3){
        deleteProduct();
      }else if(option == 4){
        displayAllProduct();
      }else if(option == 5){
        this.currentUser = null;
      }
    }while(option!=5);

  }

  void deleteProduct(){
    //deleting product on the basis of product id
    displayAllProduct();
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter Prodcut Id to Delete");
    Integer productId = sc.nextInt();
    Product prod = matchProduct(productId);
    if(prod == null){
      System.out.println("Cannot deleted no product found");
      return;
    }
    System.out.println(prod.getProduct());
    this.product.remove(prod);
    System.out.println("Product id : "+productId+" deleted");
  }

  Product matchProduct(Integer productId){
    //match the product base on unique product id
    Iterator<Product> iter = this.product.iterator();
    Product prod = null;
    while(iter.hasNext()){
      prod = iter.next();
      if(prod.isMatch(productId)){
        System.out.println("matched :: "+prod.productId);
        return prod;
      }
    }
    return null;
  }
  void updateProduct(){
    //here we are updating product for admin only use
    Scanner sc = new Scanner(System.in);
    displayAllProduct();
    System.out.println("Enter product id to update");
    Integer productId = sc.nextInt();
    Product prod = matchProduct(productId);
    System.out.println("matched product :"+prod);
    if(prod == null){
      System.out.println("Cannot upadte no product found");
      return;
    }
    modifyProduct(prod);
  }

  void modifyProduct(Product product){
    //modify product admin option
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter Name ,Description, Price");
    String name = sc.nextLine();
    String description = sc.nextLine();
    Integer price = sc.nextInt();
    product.modifyProduct(name,description,price);
    System.out.println("Prodcut Modified succesfully");
  }

  void addProduct(){
    //add product admin option
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter product Name :");
    String name = sc.nextLine();
    System.out.println("Enter Description : ");
    String description = sc.nextLine();
    System.out.println("Enter price : ");
    Integer price = sc.nextInt();
    Integer productId = this.product.size()+1;
    Product product = new Product(name,productId,description,price);
    this.product.add(product);
  }

  public MyClass(){
    //creating user and product
    User user = new User("abc","123",1,"AS",new Date(),User.Type.valueOf("ADMIN"));
    this.user.add(user);
    user = new User("xyz","123",1,"AS",new Date(),User.Type.valueOf("USER"));
    this.user.add(user);
    Product prod = new Product("prod",1,"SAF",412);
    this.product.add(prod);
    prod = new Product("new one",2,"SAF",1244);
    this.product.add(prod);
  }

  public static void main(String[] args) {
    MyClass myClass = new MyClass();
    Integer option;
    do{
      System.out.println("\nPlease Choose option");
      System.out.println("1. Create New (User or Admin) \n2. Login (User or Admin) \n3. Exit");
      Scanner sc = new Scanner(System.in);
      option = sc.nextInt();
      if(option == 1){
        myClass.createUser();
      }else if(option == 2){
        boolean isLoggedIn = myClass.doLogin();
        if(isLoggedIn == true && myClass.currentUser != null){
          System.out.println("login succesful");
          if(myClass.currentUser.isAdmin()){
            myClass.adminOption();
          }else if(myClass.currentUser.isUser()){
            myClass.userOption();
          }
        }
      }
    }while(option != 3);
  }
}
