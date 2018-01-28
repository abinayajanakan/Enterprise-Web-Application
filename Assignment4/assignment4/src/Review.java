
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review implements java.io.Serializable {
	
	String productType;
	String productName;
	double price;
	String retailer;
	String retailerZip;
	String retailerCity;
	String retailerState;
	String productOnSale;
	String manufacturer;
	String manufacturerRebate;
	String emailId;
	int userAge;
	String userGender;
	String userOccupation;
	int reviewRating;
	Date reviewDate;
	String reviewText;
  
    public Review(String productName, String productType, double price, String retailer, String retailerZip, String retailerCity,
										String retailerState, String productOnSale, String manufacturer, String manufacturerRebate,
										String emailId, int userAge, String userGender, String userOccupation, int reviewRating,
										Date reviewDate, String reviewText){
		
		this.productName = productName;
		this.productType = productType;
		this.price = price;
		this.retailer = retailer;
		this.retailerZip = retailerZip;
		this.retailerCity = retailerCity;
		this.retailerState = retailerState;
		this.productOnSale = productOnSale;
		this.manufacturer = manufacturer;
		this.manufacturerRebate = manufacturerRebate;
		this.emailId = emailId;
		this.userAge = userAge;
		this.userGender = userGender;
		this.userOccupation = userOccupation;
		this.reviewRating = reviewRating;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
    }
	
	public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getRetailerZip() {
        return retailerZip;
    }

    public void setRetailerZip(String retailerZip) {
        this.retailerZip = retailerZip;
    }

    public String getRetailerCity() {
        return retailerCity;
    }

    public void setRetailerCity(String retailerCity) {
        this.retailerCity = retailerCity;
    }

    public String getRetailerState() {
        return retailerState;
    }

    public void setRetailerState(String retailerState) {
        this.retailerState = retailerState;
    }

    public String getProductOnSale() {
        return productOnSale;
    }

    public void setProductOnSale(String productOnSale) {
        this.productOnSale = productOnSale;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturerRebate() {
        return manufacturerRebate;
    }

    public void setManufacturerRebate(String manufacturerRebate) {
        this.manufacturerRebate = manufacturerRebate;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

	
}
