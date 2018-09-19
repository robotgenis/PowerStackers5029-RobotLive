# Phone Information

To install the system to android studios follow these instuctions.

1) Go to your android studios ftc_app and click **File -> New -> New Module**

2) A window will pop up and select **Import .JAR/.AAR Package** You may have to scroll down.

3) On the next page after the **File name** line click on the three dots. Locate this repository that was downloaded onto your computer. Goto the **Info** folder and choose the **.jar** file. 

4) Then click **Finish**. Wait a few seconds for the project to build and you should see a folder named **RobotLiveLibrary_vX.X.X**.

5) Next click **File -> Project Structure**. This should be right below settings.

6) Under **Modules** click on **TeamCode**. There should be tabs accross the top, click on **Dependencies**.

7) On the right click on the **+** button. A list should pop up, click on **Module dependency**

8) Select the module named **RobotLiveLibrary_vX.X.X**. Click **Ok** till all windows disappear. The gradle should build again and you can now use the RobotLive libraries in the TeamCode section of your app. 

## Code Examples

### ftc_app Example

```
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import RobotLiveDataSending.RobotLiveData;
import RobotLiveDataSending.RobotLiveSend;

public class test extends OpMode{
    
    RobotLiveData data;
    
    @Override
    public void init() {
        
    }

    @Override
    public void loop() {
        data = RobotLiveSend.createNewRun("http://192.168.200.113");
        
        data.addStringData("Test", "Data works");
        
        RobotLiveSend.send(data, "http://192.168.200.113");
    }
}
```

### Simple Example

```
    		//Data object 
		//to add numbers to string use "String.valueOf(number);"
		RobotLiveData data = RobotLiveSend.createNewRun("http://192.168.200.113");

		//String/text
		data.addStringData("Text", "here is text data");
		
		//Create and add numbers to two arrays for chart
		ArrayList<String> x = new ArrayList<>();
		ArrayList<String> y = new ArrayList<>();
		x.add("0");
		y.add("0");
		x.add("1");
		y.add(".5");
		x.add("2");
		y.add("2");
		
		//add chart data to chart object
		data.addChartData("PID", x, y);
		
		//Add file from file location, can be video (mp4) or image (jpg)
		data.addFile("video",new File("C:/Users/Brandon/Documents/test.mp4"));
		
		//Add Live image, all images should be jpegs
		data.addLiveImage(new File("C:/Users/Brandon/Documents/image.jpg"));
		
		//send data
		RobotLiveSend.send(data,"http://localhost");
```


### Manual Looping Example
```
		RobotLiveData data;
		
		data = RobotLiveSend.createNewRun("http://192.168.200.113");
		
		
		
		//Send data with attributes in INIT
		//So you can see the data that will show up as it happens
		 ArrayList<String> x = new ArrayList<>();
		ArrayList<String> y = new ArrayList<>();
		//Must add points when first creating a chart
		x.add("0");
		y.add("0");
		data.addChartData("PID", x, y);
		
		data.addStringData("Text", "here is text data");
		
		RobotLiveSend.send(data,"http://localhost");
		
		
		
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Loop to simulate multiple data sends under the same test
		for(int i = 0; i < 100; i++){
			
			
			//Empty arraylist
			x = new ArrayList<>();
			y = new ArrayList<>();
			
			//Add a random value from 0 to 10
			x.add(String.valueOf(i));
			y.add(String.valueOf(Math.round(10*Math.random())));

			
			data.addChartData("PID", x, y);
			
			
			data.addStringData("Text", "here is text data");
			
			
			RobotLiveSend.send(data,"http://localhost");
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		data.addFile("video",new File("C:/Users/Brandon/Documents/test.mp4"));
		
		data.addLiveImage(new File("C:/Users/Brandon/Documents/image.jpg"));
		
		RobotLiveSend.send(data,"http://localhost");

```
