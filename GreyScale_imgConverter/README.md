Description:

This project is a client-server application that allows users to convert images to grayscale through a simple graphical user interface (GUI) built using JavaFX. The client program connects to a server via sockets, sends an image in Base64 format to the server, and requests the server to apply a grayscale filter. The server processes the image and sends back the grayscale version, which is then displayed on the client's GUI.

The GUI includes:

•	A view to display the original and grayscale images.
•	Buttons to connect to the server and initiate the grayscale conversion.
•	A text area to display status messages, such as connection success and server responses.


Objective:


The primary objective of this project is to demonstrate the integration of network programming and image processing in a JavaFX-based application. It allows users to:
•	Establish a connection with a remote server.
•	Send image data to the server.
•	Receive processed image data (in grayscale) from the server.
•	Display both the original and processed images on the GUI.
The project showcases how to use sockets for real-time communication, handle image files, and apply encoding/decoding techniques like Base64 for efficient data transmission.


