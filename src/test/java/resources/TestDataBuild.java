package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String address, String language, String phno, String website,
			int accuracy, double latitude, double longitude) { //io.cucumber.datatable.DataTable dataTable) 
		AddPlace p = new AddPlace();
		p.setAccuracy(accuracy);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number(phno);
		p.setWebsite(website);
		p.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
//		List<List<String>> myList = dataTable.asLists(String.class);
//		for (List<String> list : myList) {
//			p.setTypes(list);
//		}

		Location l = new Location();
		l.setLat(latitude);
		l.setLng(longitude);
		p.setLocation(l);
		return p;

	}
		
	public String deletePlacePayload(String place_id)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}" ;
	}
}