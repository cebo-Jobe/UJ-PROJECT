import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Base64;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ClientPane extends GridPane{
	
	private ImageView imageViewTop;
	private Button btnConnect;
	private Button btnGrayScale;
	private ImageView imageViewBottom;
	private TextArea txtArea;
	
	private String grayURL = "/api/GrayScale";
	Socket s = null;
	
	BufferedReader br = null;
	InputStream is = null;
	
	OutputStream os = null;
	DataOutputStream dos = null;
	BufferedOutputStream bos = null;
	
	public ClientPane() {
		setGUI();
		
		btnConnect.setOnAction((bc)-> {
			try {
				s = new Socket("localhost",4986);
				//bufferedReader for receiving a text
				is = s.getInputStream();
				br = new BufferedReader(new InputStreamReader(is));
				//DataOutputStream for sending image
				os = s.getOutputStream();
				bos = new BufferedOutputStream(os);
				dos = new DataOutputStream(bos);
				
				txtArea.appendText("Successfully connected to the server;-)");
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		});
		
		btnGrayScale.setOnAction((bg)->{
			String encodedFile = null;
			
			//read file into memory and prepare data for sending
		
			try {
				File imageFile = new File("data/images.jpeg");
				FileInputStream fis = new FileInputStream(imageFile);
				byte[] bytes = new byte[(int)imageFile.length()];
				fis.read(bytes);
				
				//Encode the file int base64 and get byte[] to send across
				encodedFile = new String(Base64.getEncoder().encodeToString(bytes));
				byte[] bytesToSend = encodedFile.getBytes();
				
				//construct and send a POST request with DataUotputStream
				dos.write(("POST "+grayURL+" HTTP/1.1\r\n").getBytes());
				dos.write(("Content-Type: "+"application/text\r\n").getBytes());
				dos.write(("Content-Length: "+encodedFile.length()+"\r\n").getBytes());
				dos.write(("\r\n").getBytes());
				dos.write(bytesToSend);
				dos.flush();
				dos.write(("\r\n").getBytes());
			
				txtArea.appendText("POST command sent;-)");
				
				//Receiving response
				String response = "";
				String line = "";
				
				while(!(line = br.readLine()).equals("")) //while there is no empty line
				{
					response = line+"\n";
				}
				System.out.println(response);
				//Receiving the image Data
				String imgData = "";
				while((line = br.readLine()) != null)
				{
					imgData += line;
				}
				
				System.out.println("Image Data: "+imgData);
				
				//Server image response format: {img:b'datareceived'} - get the datareceived
				String base64str = imgData.substring(imgData.indexOf('\''), imgData.lastIndexOf('}')-1);
				System.out.println(base64str);
				
				//Decode the base64str received
				
				byte[] decodedStr = Base64.getDecoder().decode(base64str);
				//dsiplay the gray image received
				Image gray = new Image(new ByteArrayInputStream(decodedStr));
				imageViewBottom.setImage(gray);
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
		});
	}
	
	
	
	

	private void setGUI() {
		setHgap(10);
        setVgap(10);
        setAlignment(Pos.CENTER);

        imageViewTop = new ImageView();
        btnConnect = new Button("Connect");
        btnGrayScale = new Button("GrayScale");
        Image image = new Image("file:data/images.jpeg");
        imageViewTop.setImage(image);
        txtArea = new TextArea();
        txtArea.setPrefHeight(50);
        
        //adding elements to the GUI
       add(imageViewTop, 0,0);
        add(btnConnect,0,1);
        add(btnGrayScale,1,1);
        add(txtArea,0,2,2,1);
        //add(imageViewBottom,0,3,2,1);
        
        
        
		
	}

}
