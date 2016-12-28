package BE;

public class beDrawerItem 
{
	 String ItemName;
     int imgResID;

     String title;
     boolean isSpinner;
  
     public beDrawerItem(String itemName, int imgResID) 
     {
           super();
           ItemName = itemName;
           this.imgResID = imgResID;
     }

     public beDrawerItem(boolean isSpinner) 
     {
         this(null, 0);
         this.isSpinner = isSpinner;
  	 }

     public beDrawerItem(String title) 
     {
         this(null, 0);
         this.title = title;
     }
   
     public String getItemName() 
     {
           return ItemName;
     }
     
     public void setItemName(String itemName) 
     {
           ItemName = itemName;
     }
     
     public int getImgResID() 
     {
           return imgResID;
     }
     
     public void setImgResID(int imgResID) 
     {
           this.imgResID = imgResID;
     }
     
     public String getTitle() 
     {
         return title;
     }
     
     public void setTitle(String title) 
     {
         this.title = title;
     }

     public boolean isSpinner() 
     {
         return isSpinner;
     }
}
