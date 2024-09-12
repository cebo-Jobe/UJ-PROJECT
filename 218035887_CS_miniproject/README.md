Objective

The goal of this project is to develop a JavaFX-based application for managing and analyzing power consumption data across multiple towns. The application uses a graph-based data structure to represent towns and their connections via power lines, allowing for real-time monitoring and analysis of power usage.

Key Features

Graph Representation:


Vertices represent towns.
Edges represent power lines connecting towns with associated costs (power consumption rates).
Graph Management:

Adding/Removing Towns: Users can add or remove towns from the graph.
Adding/Removing Edges: Users can add or remove power lines between towns. The application ensures that edges are unique and updates the graph accordingly.
Real-Time Analysis:

Power Consumption Tracking: The application uses a timeline to simulate power consumption changes over time, updating the power usage of each town.
Visualization: Provides real-time charts to display power consumption trends and comparisons:
Line Chart: Shows the power consumption trend over time.
Bar Chart: Displays the current power consumption of each town.
Alerts and Notifications:

Threshold Alerts: Users can set a threshold for power consumption. The application will notify users if the total power usage exceeds the specified threshold.
User Interface:

Management Interface: 

Allows users to add or remove towns and power lines, and view current connections.
Visualization Interface: Displays real-time charts and controls for starting, pausing, or stopping the simulation of power consumption.
Technical Details

Graph Traversal:

Uses Depth-First Search (DFS) to compute total power usage across the connected graph. This method accumulates power usage values from all reachable vertices.

Data Structures:

Utilizes HashMap for maintaining edges and vertices, HashSet for tracking visited nodes, and JavaFX components for user interaction and visualization.

JavaFX:

Provides a rich GUI for interaction, including charts for visualizing data and alerts for notifications.
Use Cases
Utility Management: Helps utility companies monitor and manage power consumption across various towns in real-time.
Data Analysis: Facilitates the analysis of power usage trends and identifies areas with high or low consumption.
Decision Support: Provides insights for making informed decisions regarding power distribution and infrastructure improvements.

Conclusion


This application provides a comprehensive tool for visualizing and analyzing power consumption data using a graph-based approach. By integrating real-time data analysis with a user-friendly interface, it supports effective management and decision-making for power distribution and consumption.
