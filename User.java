
import java.util.*;
class User{
  private String name;
  private Integer userId;
  private String password;
  private String address;
  private Date dob;
  private Type type;
  private ArrayList <Product> cart = new ArrayList <> ();
  public enum Type{
    USER,ADMIN;
  }
  public User(String name,String password,Integer userId,String address,Date dob,Type type){
    this.name = name;
    this.password = password;
    this.userId = userId;
    this.address = address;
    this.dob = dob;
    this.type = type;
  }
  public void addProduct(Product product){
    if(product == null){
      return;
    }
    cart.add(product);
  }
  public Integer getTotalPayable(){
    if(cart == null){
      return 0;
    }
    Iterator<Product> iter = cart.iterator();
    Integer amount = 0;
    while (iter.hasNext()) {
        amount += iter.next().getPrice();
    }
    return amount;
  }
  public boolean isAdmin(){
    return this.type == Type.ADMIN?true:false;
  }

  public boolean isUser(){
    return this.type == Type.USER?true:false;
  }

  public ArrayList<Product> getProduct(){
    return cart;
  }

  public boolean doLogin(String name,String password){
    return (this.name.equalsIgnoreCase(name) && this.password.equals(password));
  }


}
