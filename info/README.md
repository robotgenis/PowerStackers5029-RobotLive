# Phone Information

To install the system to android studios




## Code Examples

### Simple Example

```
    //Data object 
    //to add numbers to string use "String.valueOf(number);"
    RobotLiveData data = new RobotLiveData(1);

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
		RobotLiveSend.send(data);
```


### Manual Looping Example
```
    RobotLiveData data;
		
		data = new RobotLiveData(0);
		
		
		
		//Send data with attributes in INIT
		//So you can see the data that will show up as it happens
		 ArrayList<String> x = new ArrayList<>();
		ArrayList<String> y = new ArrayList<>();
		//Must add points when first creating a chart
		x.add("0");
		y.add("0");
		data.addChartData("PID", x, y);
		
		data.addStringData("Text", "here is text data");
		
		RobotLiveSend.send(data);
		
		
		
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Loop to simulate multiple data sends under the same test
		for(int i = 0; i < 100; i++){
			//empty data
			data = new RobotLiveData(0);
			
			
			//Empty arraylist
			x = new ArrayList<>();
			y = new ArrayList<>();
			
			//Add a random value from 0 to 10
			x.add(String.valueOf(i));
			y.add(String.valueOf(Math.round(10*Math.random())));

			
			data.addChartData("PID", x, y);
			
			
			data.addStringData("Text", "here is text data");
			
			
			RobotLiveSend.send(data);
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		data = new RobotLiveData(0);
		
		data.addFile("video",new File("C:/Users/Brandon/Documents/test.mp4"));
		
		data.addLiveImage(new File("C:/Users/Brandon/Documents/image.jpg"));
		
		RobotLiveSend.send(data);

```
