package com.main.java.babyapp.globalObject;

import java.util.ArrayList;
import java.util.Arrays;

import com.main.java.babyapp.model.ChildDetailsDAO;

public class GlobalObject {

	public static String webserviceUrl = "http://babyappdev.azurewebsites.net/apiv1/service/";
	
	public static ArrayList<ChildDetailsDAO> childDetailsList= new ArrayList<ChildDetailsDAO>();
	
	public static ArrayList<String> menuList= new ArrayList<String>(Arrays.asList("My Immunisation", "My Screenings", "My Growth Percentiles"));
	public static ArrayList<String> hintList= new ArrayList<String>(Arrays.asList("No entry yet", "No entry yet", "No entry yet"));
	
	public static ArrayList<String> addBioList= new ArrayList<String>(Arrays.asList("BIRTH RECORD", "PARTICULAR OF PARENTS", "SIGNIFICANT EVENTS", "NEWBORN SCREENING", "INIVESTIGATION(S) DONE (if any)", "INFORMATION ON DISCHARGE"));
	
	public static ArrayList<String> doseList= new ArrayList<String>(Arrays.asList("First Dose", "Second Dose", "Third Dose"));
	public static ArrayList<String> doseDateList= new ArrayList<String>(Arrays.asList("12/02/16", "16/02/16", "20/02/16"));
	
	public static ArrayList<String> screeningList= new ArrayList<String>(Arrays.asList("4-8 weeks", "3-5 months", "6-12 months", "15-18 months", "2-3 years", "4-6 years"));
	public static ArrayList<String> screeningHintList= new ArrayList<String>(Arrays.asList("Taken: 10/02/2016", "Next due: 15/02/2016", "Set reminder", "Set reminder", "Set reminder", "Set reminder"));
	
	public static ArrayList<String> growthList= new ArrayList<String>(Arrays.asList("Percentiles of Head Circumference-for-age", "Percentiles of Weight-for-age", "Percentiles of Height-for-age", "Percentiles of Body Mass Index-for-age", "Percentiles of Hearing-for-age", "Percentiles of Eye sight-for-age"));
	
	//My Screening list item object
	static String str1= "I ensure that bolsters, pillows, blankets and palstic bags are kept away from my baby to avoid unintentional suffocation. I always place my baby to sleep on his back";
	static String str2="I do not use a sarong cradle for my child nor allow him/her to sleep on the same bed as me to avoid rolling onto and suffocating him/her. My baby sleeps in a cot which meets safety standards";
	static String str3="When preparing the water for my child's bath, I run cold water into the bathtub first followed by hot water, to prevent scalds";
	static String str4="I never leave my baby unattended in the bathtub";
	public static ArrayList<String> my_scr_item_List= new ArrayList<String>(Arrays.asList(str1, str2, str3, str4));
	public static ArrayList<String> my_scr_item_subList= new ArrayList<String>(Arrays.asList("", "", "", ""));
	
	public static ArrayList<String> medication_List= new ArrayList<String>(Arrays.asList("ANTIHISTAMINES", "FEVER MEDICATIONS", "COUGH SUPPRESANTS", "COUGH EXPETORANTS", "MUCOLYTICS", "MIXED COUGH PREPARATIONS", "NASAL", "LOZENGES", "ANTIBIOTICS", "ANTIEMETICS", "ANTISPASMODIC", "TOPICAL"));
	public static ArrayList<String> medication_SubList= new ArrayList<String>(Arrays.asList("Examples: Proin gravida, nibh vel velit, aliquet", "Examples: Proin gravida, nibh vel velit, aliquet","Examples: Proin gravida, nibh vel velit, aliquet","Examples: Proin gravida, nibh vel velit, aliquet", "Examples: Proin gravida, nibh vel velit, aliquet", "Examples: Proin gravida, nibh vel velit, aliquet", "Examples: Proin gravida, nibh vel velit, aliquet", "Examples: Proin gravida, nibh vel velit, aliquet", "Examples: Proin gravida, nibh vel velit, aliquet", "Examples: Proin gravida, nibh vel velit, aliquet", "Examples: Proin gravida, nibh vel velit, aliquet", "Examples: Proin gravida, nibh vel velit, aliquet"));
	
	public static ArrayList<String> immunisation_List= new ArrayList<String>(Arrays.asList("BCG", "HEPATITIS B", "DTaP", "MMR"));
	public static ArrayList<String> immunisation_SubList= new ArrayList<String>(Arrays.asList("Examples: Proin gravida, nibh vel velit, aliquet", "Examples: Proin gravida, nibh vel velit, aliquet", "Examples: Proin gravida, nibh vel velit, aliquet", "Examples: Proin gravida, nibh vel velit, aliquet"));
	
	static private String personalSocial_str1 = "Your child displays excitement like kicking legs, moving arms, on seeing an attractive toy. (Excites at a toy)";
	static private String personalSocial_str2 = "Your child will try to get a toy that he enjoys when it is out of reach by stretching his arms or body. (Works for a toy out of reach)";
	static private String personalSocial_str3 = "Your child seems to be shy or wary of strangers. (Reacts to strangers)";
	static private String personalSocial_str4 = "When you face your child, say bye-bye and wave to him, he responds by waving his arm, hand and fingers without his hands or arms being touched.";
	public static ArrayList<String> personalSocial_item_List= new ArrayList<String>(Arrays.asList(personalSocial_str1, personalSocial_str2, personalSocial_str3, personalSocial_str4));
	public static ArrayList<String> personalSocial_subList= new ArrayList<String>(Arrays.asList("Age 5.5 months", "Age 6.5 months", "Age 10 months", "Age 10.5 months"));
}
