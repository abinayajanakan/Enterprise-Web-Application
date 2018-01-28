

import java.util.HashMap;

public class ConsoleHashMap{
	public static HashMap<String, Console> microsoft = new HashMap<String, Console>();
	public static HashMap<String, Console> sony = new HashMap<String, Console>();
	public static HashMap<String, Console> nintendo = new HashMap<String, Console>();
	
	//laptops section
	public static HashMap<String, Console> lenovo = new HashMap<String, Console>();
	public static HashMap<String, Console> asus = new HashMap<String, Console>();
	public static HashMap<String, Console> hp = new HashMap<String, Console>();
	//laptops section
	
	// //tablets section
	// public static HashMap<String, Console> apple = new HashMap<String, Console>();
	// public static HashMap<String, Console> microsoft = new HashMap<String, Console>();
	// public static HashMap<String, Console> samsung = new HashMap<String, Console>();
	// //tablets section
	
	//tvs section
	public static HashMap<String, Console> sonybravia = new HashMap<String, Console>();
	public static HashMap<String, Console> toshiba = new HashMap<String, Console>();
	public static HashMap<String, Console> samsung = new HashMap<String, Console>();
	//tvs section
	
	
	public static String string_microsoft = "Microsoft";
	public static String string_sony = "Sony";
	public static String string_nintendo = "Nintendo";
	
	//laptopstrings section
	public static String string_lenovo = "Lenovo";
	public static String string_asus = "Asus";
	public static String string_hp = "Hp";
	//laptopstrings section

	//laptopstrings section
	public static String string_sonybravia = "Sony Bravia";
	public static String string_toshiba = "Toshiba";
	public static String string_samsung = "Samsung";
	//laptopstrings section
	
	public ConsoleHashMap() {
		HashMap<String, Accessory> accessories;
		if(microsoft.isEmpty()){
			Accessory xboxone_wc = new Accessory("Controller", 40.00, "XBOX controller.jpg", "Microsoft","New",10);
			Accessory xboxone_sh = new Accessory("Turtle Beach Headset", 50.00, "Turtle Beach Headset.jpg", "Microsoft","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("xboxone_wc", xboxone_wc);
			accessories.put("xboxone_sh", xboxone_sh);			
			Console xboxone = new Console("APPLE",399.00,"xbox1.jpg","Microsoft","New",10,accessories);
			microsoft.put("xboxone", xboxone);
			
			Accessory xbox360_mr = new Accessory("Speeding Wheel", 40.00, "XBOX360-SpeedWheel.jpg", "Microsoft","New",10);
			Accessory xbox360_wa = new Accessory("Wireless Adapter", 50.00, "xbox360_wa.png", "Microsoft","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("xbox360_mr", xbox360_mr);
			accessories.put("xbox360_wa", xbox360_wa);
			Console xbox360 = new Console("XBox 360",299.00,"xbox360.jpg","Microsoft","New",10,accessories);			
			microsoft.put("xbox360", xbox360);
		}

		if(sony.isEmpty()){			

			
		
			accessories = new HashMap<String, Accessory>();

			Console ps4 = new Console("PS4",349.00,"PS4-console-bundle.jpg","Sony","New",10,accessories);
			sony.put("ps4", ps4);			
		}

		if(nintendo.isEmpty()){

		}
		
		//lenovo laptops
		if(lenovo.isEmpty()){
			for(int i = 0; i<=5; i++)
			{
				Accessory charger = new Accessory("Lenovo Charger", (20.00+i), "lenovocharger"+i+".jpg", "Lenovo","New",5);
				Accessory laptopbag = new Accessory("Lenovo Laptopbag", (10.00+i), "lenovobag"+i+".jpg", "Lenovo","New",5);
				accessories = new HashMap<String, Accessory>();
				String accessoryKey = "charger"+i;
				String accKey = "laptopbag"+i;
				String hmKey = "lenovo_yoga"+i;
				accessories.put(accessoryKey, charger);
				accessories.put(accKey, laptopbag);			
				Console lenovo_yoga = new Console("Lenovo Yoga Series"+i,(399.00+i),"lenovolaptop"+i+".jpg","Lenovo","New",50,accessories);
				lenovo.put(hmKey, lenovo_yoga);				
			}
			
			
			// Accessory headphone = new Accessory("Lenovo Headphones", 12.00, "lenovoheadphone.jpg", "Lenovo","New",10);
			// Accessory laptopbag1 = new Accessory("Lenovo Laptopbag", 10.00, "lenovobag1.jpg", "Lenovo","New",5);
			// accessories = new HashMap<String, Accessory>();
			// accessories.put("headphone", headphone);
			// accessories.put("laptopbag1", laptopbag1);
			// Console lenovo_new = new Console("Lenovo N Series",499.00,"lenovolaptop1.jpg","Lenovo","New",10,accessories);			
			// lenovo.put("lenovo_new", lenovo_new);
		}
		
		//asus
		if(asus.isEmpty()){
			Accessory asuscharger = new Accessory("Asus Charger", 20.00, "asuscharger.jpg", "Asus","New",5);
			Accessory asusbattery = new Accessory("Asus Battery", 10.00, "asusbattery.jpg", "Asus","New",5);
			accessories = new HashMap<String, Accessory>();
			accessories.put("charger", asuscharger);
			accessories.put("asusbattery", asusbattery);			
			Console asuslaptop = new Console("Asus Laptop",399.00,"asuslaptop.jpg","Asus","New",50,accessories);
			asus.put("asuslaptop", asuslaptop);
			
			// Accessory laptopbag = new Accessory("Asus laptopbag", 20.00, "laptopbag.jpg", "Asus","New",5);
			// Accessory asusbattery = new Accessory("Asus Battery", 10.00, "asusbattery.jpg", "Asus","New",5);
			// accessories = new HashMap<String, Accessory>();
			// accessories.put("laptopbag", laptopbag);
			// accessories.put("asusbattery", asusbattery);			
			// Console asuslaptop = new Console("Asus Laptop",399.00,"asuslaptop.jpg","Asus","New",50,accessories);
			// lenovo.put("asuslaptop", asuslaptop);
		 }
		// //hp laptops
		if(hp.isEmpty()){
			Accessory hpkeyboard = new Accessory("HP Keyboard", 40.00, "hpkeyboard.jpg", "HP","New",10);
			Accessory hpscanner = new Accessory("HP Scanner", 50.00, "hpscanner.jpg", "HP","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("hpkeyboard", hpkeyboard);
			accessories.put("hpscanner", hpscanner);			
			Console hplaptop = new Console("HP Laptop",399.00,"hplaptop.jpg","HP","New",10,accessories);
			hp.put("hplaptop", hplaptop);
			
			// Accessory xbox360_mr = new Accessory("Speeding Wheel", 40.00, "XBOX360-SpeedWheel.jpg", "Microsoft","New",10);
			// Accessory xbox360_wa = new Accessory("Wireless Adapter", 50.00, "xbox360_wa.png", "Microsoft","New",10);
			// accessories = new HashMap<String, Accessory>();
			// accessories.put("xbox360_mr", xbox360_mr);
			// accessories.put("xbox360_wa", xbox360_wa);
			// Console xbox360 = new Console("XBox 360",299.00,"xbox360.jpg","Microsoft","New",10,accessories);			
			// microsoft.put("xbox360", xbox360);
		}
	}
}
