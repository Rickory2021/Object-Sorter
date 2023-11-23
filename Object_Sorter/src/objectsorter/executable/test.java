package objectsorter.executable;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.management.openmbean.CompositeDataInvocationHandler;

import java.io.InputStreamReader;

import objectsorter.structure.ElementComponent;
import objectsorter.structure.ElementComponentInfo;
import objectsorter.structure.ElementComponentInfoComparable;
import objectsorter.structure.ElementListQoL;
import objectsorter.structure.ElementObject;
import objectsorter.structure.ElementSorter;
import objectsorter.structure.ElementSorterPrecedenceType;
import objectsorter.textanalyzer.csv.CsvTableParser;
import objectsorter.textanalyzer.html.HTMLDOMParser;
import objectsorter.textanalyzer.html.HtmlTag;

public class test {

	public static void main(String[] args) {
		//main1();
		//main2();
		//main3();
		TeppanCafeTipReport();
	}
	
	public static void main1() {
		System.out.println("/");
		String text = "la conférence, commencera à 10 heures ?";
		String result = text.replaceAll("[\ud83c\udf00-\ud83d\ude4f]|[\ud83d\ude80-\ud83d\udeff]", "");
		System.out.println(result);
		System.out.println("🤟"+"🤟".matches("\\x{0001f91f}"));
		System.out.println("☝🏻"+"☝🏻".matches("\\x{0001f91f}"));
		// "[\\uD800-\\uDB7F][\\uDC00-\\uDFFF]";
		String regexEmoji = "[\\x{00010000}-\\x{000EFFFF}]";
		
		String[] stringEmoji = new String[] {"😀","😂","🤟","☕","☝🏻"};
		for(String str:stringEmoji) {
			System.out.println(str+"\t"+str.matches(regexEmoji));
		}
		"☕😀😂🤟☕☝🏻🗻🗻👌🏿𲎯𲎯".codePoints().mapToObj(Integer::toString).forEach(System.out::println);
		String[] stringTest = new String[] {"foo-bar", "Foo-bar", "baz-©","annotation-xml","my-element",
				"my-custom-tag","r-1","open-234","html-🤟a","message-box","a-⭐","myelement","my_element",
				"myElement","my-element","5-column","🤟-house"};
		for(String str:stringTest) {
			System.out.println(str+"\t"+HtmlTag.isValidCustomTag(str));
		}

		String[] stringTest2 = new String[] {"<foo-bar>", "   <Foo-bar/>", "   baz-©>","sasdasd<annotation-xml  > 2 ","<>my-element",
				"my-custom-tag","<r-1>","open-234","html-🤟a","message-box","a-⭐","myelement","my_element",
				"myElement","my-element","5-column","🤟-house"};
		for(String str:stringTest2) {
			System.out.println(str+"\t|"+HtmlTag.clearArrowTagString(str)+"|");
		}
		
		
		
		System.out.println((int)'\0');
		System.out.println(">"+(char)(-1)+"<");
		System.out.println(">"+(char)Integer.MAX_VALUE);
		System.out.println((char)(-1)==(char)Integer.MAX_VALUE?"True":"False");
		
		
		ArrayList<Integer> Test = new ArrayList<>();
		Test.add(1);
		Test.add(2);
		Test.add(3);
		Test.remove(1);
		Test.add(1,5);
		System.out.println(Test);
		
		BufferedReader systemReader = new BufferedReader(new InputStreamReader(System.in));
		String[] artistName = {"AAA", "AAAMYY", "Ado", "Aimer", "Amazarashi", "Anna", "Asako Toki", "Asca", "Ayuma Imazu",
				"back number", "BBHF", "THE CHARM PARK", "Chippoke Ohashi", "Daoko", "Egoist", "Eill", "Eve", "FIVE NEW OLD", "flumpool",
				"fredric", "Fuji Kaze", "Gen Hoshino", "go!go!vanillas", "haruno", "Hello Sleepwalkers", "HENTAI SHINSHI CLUB",
				"Hikaru Utada", "HIRAIDAI", "I Don't Like Mondays.", "imase", "indigo la End", "iri", "KAMI WA SAIKORO WO FURAN.....",
				"Kenshi Yonezu", "King Gnu", "Lilas Ikuta", "Maica_n", "Novelbright", "OFFICIAL HIGE DANDISM", "Omoinotake","THE ORAL CIGARETTES",
				"ReN", "Roel", "Rude-a", "Taichi Mukai", "takayan", "Tani Yuuki", "tonun", "TUYU", "vaundy", "wacci", "YAJICO GIRL", "yama", 
				"YOASOBI", "Yoru no Hitowarai", "Yuuri"};

		try {
			ArrayList<Integer> active = new ArrayList<>();
			ArrayList<ElementObject> list = new ArrayList<ElementObject>();
			for(int i = (int)(Math.random()*6)+5; i>0;i--) {
				int index = (int)(Math.random()*artistName.length);
				while(active.contains(index))
					index = (int)(Math.random()*artistName.length);
				active.add(index);
				ElementObject object = new ElementObject();
				
				ArrayList<String> listInfo = new ArrayList<>();
				listInfo.add("info1");
				listInfo.add("info2");
				ElementComponentInfo<ArrayList<String>> infocomp = new ElementComponentInfo<ArrayList<String>>(listInfo);
				ElementComponent component0 = new ElementComponent("Info", infocomp);
				object.addComponent(component0,ElementListQoL.UNIQUEFINAL);
				
				
				ElementComponentInfoComparable<String> name = new ElementComponentInfoComparable<String>(artistName[index]);
				System.out.println("Component:"+name.getClass());
				ElementComponent component = new ElementComponent("Artist Name", artistName[index]);
				
				System.out.println("Artist:"+component.getcomponentInfo().getClass());
				System.out.println(component.getcomponentInfo());
				object.addComponent(component,ElementListQoL.DUPLICATENAMEUNSORTED);
				System.out.println("ARTIST DONE");
				
				
				/*System.out.println("Initial");
				System.out.println(object);
				System.out.println("1st");
				object.addComponent("Score", (int)(Math.random()*1000),true);
				System.out.println(object.getComponent("Score"));
				System.out.println(object);
				System.out.println("2nd");*/
				System.out.println();
				ElementComponentInfoComparable<Integer> score = new ElementComponentInfoComparable<Integer>((Integer)((int)(Math.random()*1000)));
				ElementComponent component2 = new ElementComponent("Score", ((int)(Math.random()*1000)));
				object.addComponent(component2,ElementListQoL.DUPLICATENAMEUNSORTED);
				ElementComponentInfoComparable<Integer> flag = new ElementComponentInfoComparable<Integer>(1);
				if(Math.random()>0.5) {
					flag = new ElementComponentInfoComparable<Integer>(0);
				}	
				ElementComponent component3 = new ElementComponent("Flag", flag);
				object.addComponent(component3,ElementListQoL.DUPLICATENAMEUNSORTED);
				
				//System.out.println(object.getComponent("Score"));
				//System.out.println("FINAL");
				//System.out.println(object);
				//Collections.sort(object.getComponentList());
				list.add(object);
				
			}
			
			for(int i = 0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
			
			System.out.println("\n\n\n\n\n\n\n");
			
			
			ArrayList<ElementSorterPrecedenceType> orderOfPrecedence = new ArrayList<>();
			
			//orderOfPrecedence.add(new ElementSorterPrecedenceType("Artist Name", true));
			ElementSorter byArtistName = new ElementSorter("byArtisy",orderOfPrecedence);
			//Collections.sort(list, byArtistName);
			//orderOfPrecedence.add(new ElementSorterPrecedenceType("Flag", false));
			orderOfPrecedence.add(new ElementSorterPrecedenceType("Score", false));
			
			ElementSorter byScore = new ElementSorter("byScore",orderOfPrecedence);
			Collections.sort(list, byScore);
			for(int i = 0;i<list.size();i++) {
				System.out.println(list.get(i));
			}
			/* 
			 * 
			 * Check for>
			 * Name Score Flag Initial Date Recent Date
			 * ALL ASCENDING AND DESCENDING
			 * 
			 * Name
			 * Score
			 * Flag
			 * Initial Date
			 * Recent Date
			 * 
			 * Tag+Score
			 * Tag+Name
			 * Tag+Tag+Tag
			 */
			
			/*ArtistSorter artistSorter = new ArtistSorter();
			//artistSorter.addPrecedence(new String[]{"Recent Date","Descending"});
			artistSorter.addPrecedence(new String[]{"Flag-NEW","Descending"});
			artistSorter.addPrecedence(new String[]{"Score","Descending"});
			list.sort(artistSorter);

			
			SongSorter songSorter = new SongSorter();
			// NEED TO TEST
			// Name Score Flag
			// Initial State Recent Date Placement Count
			//songSorter.addPrecedence(new String[]{"Flag-NEW","Descending"});
			songSorter.addPrecedence(new String[] {"Placement Count", "Descending"});
			for(int i = 0;i<list.size();i++) {
				list.get(i).getSongs().sort(songSorter);
			}
			
			PlacementSorter placeSorter = new PlacementSorter();
			//placeSorter.addPrecedence(new String[] {"Place","Ascending"});
			placeSorter.addPrecedence(new String[] {"Date", "Ascending"});
			for(int i = 0;i<list.size();i++) {
				for(int j = 0; j<list.get(i).getSongs().size();j++) {
					list.get(i).getSongs().get(j).getPlacements().sort(placeSorter);
				}			
			}
			
			for(int i = 0;i<list.size();i++) {
				System.out.println(list.get(i));
			}*/
			// Serialization
			ElementObject object = list.get(list.size()-1);
			String filename = "shubham.txt";
			
	        try {
	        	
	            // Saving of object in a file
	            FileOutputStream file = new FileOutputStream
	                                           (filename);
	            ObjectOutputStream out = new ObjectOutputStream
	                                           (file);
	            System.out.println("WATS");
	            // Method for serialization of object
	            out.writeObject(object);
	            out.close();
	            file.close();
	 
	            System.out.println("Object has been serialized\n"
	                              + "Data before Deserialization.");
	            System.out.println(object);
	        }
	 
	        catch (IOException ex) {
	            System.out.println("IOException is caught1");
	        }
	 
	        object = null;
	 
	        // Deserialization
	        try {
	 
	            // Reading the object from a file
	            FileInputStream file = new FileInputStream
	                                         (filename);
	            ObjectInputStream in = new ObjectInputStream
	                                         (file);
	 
	            // Method for deserialization of object
	            object = (ElementObject)in.readObject();
	 
	            in.close();
	            file.close();
	            System.out.println("Object has been deserialized\n"
	                                + "Data after Deserialization.");
	            System.out.println(object);
	 
	            // System.out.println("z = " + object1.z);
	        }
	 
	        catch (IOException ex) {
	            System.out.println("IOException is caught");
	        }
	 
	        catch (ClassNotFoundException ex) {
	            System.out.println("ClassNotFoundException" +
	                                " is caught");
	        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main2() {
		HTMLDOMParser test1=new HTMLDOMParser("Database");
		test1.analyzeHtml("Sales Report _ Tokyo Teppan Cafe.html");
		//System.out.println(test1.getDom());
		ElementObject dom = test1.getDom();
		//System.out.println(dom.getObjectGroup(new String[] {"html"}));
		ArrayList<ElementObject> searchedList = 
				dom.getObjectGroup(new String[] {"html", "body","div","div","div","div","b", "table", "tbody", "tr"},ElementListQoL.DUPLICATENAMEUNSORTED);
		//"html", "body", "div", "div" ,"div", "b", "table", "tbody", "tr", "td"
		/*for(ElementObject object : searchedList) {
			System.out.println(object.getComponent("text"));
		}*/
		/*for(int i = 0; i<searchedList.size();i++) {
			if(searchedList.get(i).getComponent("text")==null) {
				System.out.print(String.format("%15s\t","null"));
			}else {
				System.out.print(String.format("%15s\t",searchedList.get(i).getComponent("text").getcomponentInfo()));
			}
			
			if(i%16==15)System.out.println();
		}*/
		
		//System.out.println(HTMLDOMParser.constructTable(searchedList, "text", 16));
		System.out.println("TABLE STRING");
		System.out.println(ElementListQoL.getTableString(HTMLDOMParser.constructTable(searchedList, "text", 16), 15));
		System.out.println("FINISHED TABLE STRING");
		ArrayList<ElementObject> dateList = dom.getObjectGroup(new String[] {"html", "body","div","div","div","div","h6"},ElementListQoL.DUPLICATENAMEUNSORTED);
		System.out.println("DATE LIST");
		for(int i = 0; i<dateList.size();i++) {
			if(dateList.get(i).getComponent("text")==null) {
				System.out.print(String.format("%15s\t","null"));
			}else {
				System.out.print(String.format("%15s\t",dateList.get(i).getComponent("text").getcomponentInfo()));
			}
		}
	}
	
	public static void main3() {
		CsvTableParser test1 = new CsvTableParser("Database");
		test1.analyzeCsv("clockinReport.csv");
		System.out.println(test1.getTable());
		System.out.println(ElementListQoL.getTableString(test1.getTable(), 15));
	}
	
	public static void TeppanCafeTipReport() {
		ArrayList<ElementObject> employeeDailyTipReportList = new ArrayList<>();
		// Front of House
		String[] frontOfHouseArr = new String[] {"Ameena","Brianna","Casey","Danery","Jacob","Lexi","Isaiah"};
		// Directory Path and File Name
		String dailySalesDirectoryString = "C:\\Users\\ricky\\OneDrive\\Desktop\\Object Sorter Database\\Teppan Cafe\\Daily Sales Report",
				clockInDirectoryString = "C:\\Users\\ricky\\OneDrive\\Desktop\\Object Sorter Database\\Teppan Cafe\\Clock In Report";
		File dailySalesDirectoryPath = new File(dailySalesDirectoryString);
		File clockInDirectoryPath = new File(clockInDirectoryString);
		
		String dailySalesFileNameArr[] = dailySalesDirectoryPath.list();
		String clockInFileNameArr[] = clockInDirectoryPath.list();
		for(String arr:clockInFileNameArr) {
			System.out.println(arr);
		}
		ArrayList<ElementObject> dailySalesTableList = new ArrayList<>();
		ArrayList<ElementObject> clockInTableList = new ArrayList<>();
		
		for(String dailySalesFileName : dailySalesFileNameArr) {
			HTMLDOMParser htmlParser = new HTMLDOMParser(dailySalesDirectoryString);
			htmlParser.analyzeHtml(dailySalesFileName);
			ArrayList<ElementObject> tableList = 
					htmlParser.getDom().getObjectGroup(new String[] {"html", "body","div","div","div","div","b", "table", "tbody", "tr"},ElementListQoL.DUPLICATENAMEUNSORTED);
			//System.out.println(tableList.size());
			/*for(int i = 0; i<tableList.size();i++) {
				if(tableList.get(i).getComponent("text")==null) {
					System.out.print(String.format("%15s\t","null"));
				}else {
					System.out.print(String.format("%15s\t",tableList.get(i).getComponent("text").getcomponentInfo()));
				}
				
				if(i%16==15)System.out.println();
			}*/
			//System.out.println(tableList.size()/16.0);
			ElementObject table = HTMLDOMParser.constructTable(tableList, "text", 16);
			//System.out.println(table);
			//System.out.println(ElementListQoL.getTableString(table, 15));
			ArrayList<ElementObject> dateList = htmlParser.getDom().getObjectGroup(new String[] {"html", "body","div","div","div","div","h6"},ElementListQoL.DUPLICATENAMEUNSORTED);
			StringBuilder objectName = new StringBuilder();
			for(ElementObject dateObject: dateList) {
				objectName.append(dateObject.getComponent("text").getcomponentInfo().toString().trim()+" ");
			}
			//System.out.println(objectName);
			table.setObjectName(objectName.toString());
			// Day Sales Report 2023-11-20	
			dailySalesTableList.add(table);
		}
		
		for(String clockInFileName : clockInFileNameArr) {
			//System.out.println(clockInFileName);
			CsvTableParser csvParser = new CsvTableParser(clockInDirectoryString);
			csvParser.analyzeCsv(clockInFileName);
			ElementObject table = csvParser.getTable();
			String dateName = table.getObjectList().get(1).getComponent("Date").getcomponentInfo().toString().trim();
			String month = dateName.substring(0,3).toLowerCase(), day = dateName.substring(3,dateName.length());
			String[] monthString = {"jan","feb","mar","apr", "may","jun","jul","aug","sep","oct","nov","dec"},
					monthInteger = {"01","02","03","04","05","06","07","08","09","10","11","12"};
			String objectName=null;
			for(int i = 0;i<12;i++) {
				if(month.equals(monthString[i])) {
					objectName = monthInteger[i]+"-"+day;
					break;
				}
			}
			//System.out.println(objectName);
			//System.out.println(month+">>");
			table.setObjectName(objectName);
			// Nov20
			clockInTableList.add(table);
		}
		
		for(ElementObject dailySalesTable: dailySalesTableList) {
			//System.out.println(ElementListQoL.getTableString(dailySalesTable, 15));
			//System.out.println(dailySalesTable);
		}
		
		for(ElementObject clockInTable: clockInTableList) {
			//System.out.println(ElementListQoL.getTableString(clockInTable, 15));
		}
		for(ElementObject clockInTable: clockInTableList) {
			String date = clockInTable.getObjectName();
			ElementObject dailySalesTable = null;
			//System.out.println();
			//System.out.println("HUH");
			for(ElementObject tempSalesTable: dailySalesTableList) {
				//System.out.println(tempSalesTable.getObjectName());
				//System.out.println(date);
				//System.out.println(tempSalesTable.getObjectName());
				if(tempSalesTable.getObjectName().contains(date)) {
					dailySalesTable=tempSalesTable;
					//System.out.println("FOUND");
					break;
				}
			}
			// frontOfHouseArr
			ArrayList<ElementObject> shiftList = new ArrayList<>();
			for(ElementObject clockInRow: clockInTable.getObjectList()) {
				//System.out.println(clockInRow);
				ElementObject shift = null;
				for(String frontOfHouseString : frontOfHouseArr) {
					//System.out.println(frontOfHouseString+"<<>>"+clockInRow.getComponent("Name"));
					//System.out.println(clockInRow);
					if((clockInRow.getComponent("Name").getcomponentInfo()).toString().equals(frontOfHouseString)) {
						//System.out.println("FOUNDDED");
						shift = new ElementObject(frontOfHouseString);
						break;
					}
				}
				if(shift==null)continue;
				//System.out.println();
				shift.addComponent("Clock In", clockInRow.getComponent("Time In").getcomponentInfo().toString(),ElementListQoL.DUPLICATENAMEUNSORTED);
				shift.addComponent("Clock Out", clockInRow.getComponent("Time Out").getcomponentInfo().toString(),ElementListQoL.DUPLICATENAMEUNSORTED);
				shift.addComponent("Hours", clockInRow.getComponent("Hours").getcomponentInfo().toString(),ElementListQoL.DUPLICATENAMEUNSORTED);
				shift.addComponent("Clock In Minutes", (int)toMinutes(clockInRow.getComponent("Time In").getcomponentInfo().toString()),ElementListQoL.DUPLICATENAMEUNSORTED);
				shift.addComponent("Clock Out Minutes", (int)toMinutes(clockInRow.getComponent("Time Out").getcomponentInfo().toString()),ElementListQoL.DUPLICATENAMEUNSORTED);
				shift.addComponent("Total Minutes", (int)shift.getComponent("Clock Out Minutes").getcomponentInfo().getInfo()
						-(int)shift.getComponent("Clock In Minutes").getcomponentInfo().getInfo(),ElementListQoL.DUPLICATENAMEUNSORTED);
				//System.out.println(shift);
				shiftList.add(shift);
			}
			ArrayList<ElementObject> tipList = new ArrayList<>();
			//System.out.println(ElementListQoL.getTableString(dailySalesTable, 15));
			for(ElementObject dailySalesRow:dailySalesTable.getObjectList()) {
				//System.out.println(dailySalesRow.getComponent("tip"));
				//System.out.println(dailySalesRow.getComponent("tip").getcomponentInfo().toString());
				//System.out.println(dailySalesRow);
				if(dailySalesRow.getComponent("tip").getcomponentInfo().toString()!=null &&
						!dailySalesRow.getComponent("tip").getcomponentInfo().toString().equals("tip")) {
					ElementObject order = new ElementObject("Tipped Order "+dailySalesRow.getComponent("No"));
					order.addComponent("Time", dailySalesRow.getComponent("Time").getcomponentInfo().toString(),ElementListQoL.DUPLICATENAMEUNSORTED);
					order.addComponent("Tip", dailySalesRow.getComponent("tip").getcomponentInfo().toString(),ElementListQoL.DUPLICATENAMEUNSORTED);
					order.addComponent("Time Minutes", toMinutes(dailySalesRow.getComponent("Time").getcomponentInfo().toString()),ElementListQoL.DUPLICATENAMEUNSORTED);
					order.addComponent("Tip Float", Double.parseDouble(dailySalesRow.getComponent("tip").getcomponentInfo().toString().trim()),ElementListQoL.DUPLICATENAMEUNSORTED);
					//System.out.println(order);
					tipList.add(order);
				}
				//System.out.println("DONE");
			}
			
			// We now have the tip List and Shift List, Now we have to Create Per Entity and get their hours+TIp
			ArrayList<ElementObject> employeeList = new ArrayList<>();
			//frontOfHouseArr
			for(String frontOfHouseString: frontOfHouseArr) {
				ElementObject employee = new ElementObject(frontOfHouseString);
				employee.addComponent("Hours", "n/a", ElementListQoL.DUPLICATENAMEUNSORTED);
				employee.addComponent("Tip", 0.0, ElementListQoL.DUPLICATENAMEUNSORTED);
				employee.addComponent("Hours Minutes", 0, ElementListQoL.DUPLICATENAMEUNSORTED);
				//System.out.println(employee);
				employeeList.add(employee);
			}
			// Add Hours of Work
			for(ElementObject shift: shiftList) {
				String employeeString = shift.getObjectName();
				for(ElementObject employee : employeeList) {
					if(employee.getObjectName().equals(employeeString)) {
						int currentMinutes = (int)employee.getComponent("Hours Minutes").getcomponentInfo().getInfo();
						int newMinutes = (int)shift.getComponent("Total Minutes").getcomponentInfo().getInfo();
						employee.getComponent("Hours Minutes").setcomponentInfo(new ElementComponentInfo<Integer>(newMinutes+currentMinutes));
					}
				}
			}
			// Adding the Tips
			ArrayList<ElementObject> activeEmployee = new ArrayList<>();
			for(ElementObject order: tipList) {
				//System.out.println(order);
				double tip = (double)order.getComponent("Tip Float").getcomponentInfo().getInfo();
				int minuteTime = (int)order.getComponent("Time Minutes").getcomponentInfo().getInfo();
				for(ElementObject shift: shiftList) {
					//System.out.println(shift);
					if((int)shift.getComponent("Clock In Minutes").getcomponentInfo().getInfo()<=minuteTime && 
							(int)shift.getComponent("Clock Out Minutes").getcomponentInfo().getInfo()>minuteTime) {
						// Worked During that time
						String employeeString = shift.getObjectName();
						for(ElementObject employee:employeeList) {
							if(employee.getObjectName().equals(employeeString)) {
								activeEmployee.add(employee);
							}
						}
					}
				}
				// Add and Divide Evenly
				//System.out.println("Origin Tip: "+ tip + "\tSplit by: "+activeEmployee.size());
				//System.out.println("ORDER TIME: "+minuteTime);
				for(ElementObject emp: activeEmployee) {
					System.out.println(emp);
				}
				tip/=activeEmployee.size();
				for(ElementObject employee:activeEmployee) {
					double currentTip = (double)employee.getComponent("Tip").getcomponentInfo().getInfo();
					//System.out.println(currentTip + "+"+ tip);
					employee.getComponent("Tip").setcomponentInfo(new ElementComponentInfo<Double>(currentTip+tip));
					//System.out.println(employee.getComponent("Tip"));
				}
				activeEmployee.clear();
			}
			System.out.println(clockInTable.getObjectName());
			for(ElementObject employee:employeeList) {
				if((double)employee.getComponent("Tip").getcomponentInfo().getInfo()!=0.0) {
					System.out.println(employee);
				}
				
			}
			System.out.println("\n\n");
		}
	}
	
	public static int toMinutes (String militaryTime) {
		int minutes = 0;
		String[] splitted = militaryTime.trim().split(":");
		minutes+= Integer.parseInt(splitted[0])*60+Integer.parseInt(splitted[1]);
		return minutes;
	}

}
