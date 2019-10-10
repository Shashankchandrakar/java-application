
import java.util.*;
class Product{
  private String name;
   Integer productId;
  private String description;
  private Integer price;

  public Integer getPrice(){
    return this.price;
  }

  public Product(String name, Integer productId,String description,Integer price){
    this.name = name;
    this.productId = productId;
    this.description = description;
    this.price = price;
  }

  public void modifyProduct(String name,String description,Integer price){
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public String getProduct(){
    String productInfo;
    productInfo ="\n Product Id : "+this.productId+ "\n Product Name :"+this.name+"\n Description :"+this.description+
      "\n Price "+this.price;
    return productInfo;
  }

  public boolean isMatch(Integer productId){
    return this.productId.equals(productId);
  }
}
