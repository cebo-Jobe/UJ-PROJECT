import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @author Cebo Sithole 218035887
 * Eskom Problem Solution
 *
 */

public class FileHandlerClass {

	private static Scanner sc;

	 /**
	 * @param username, the user name that user enters
	 * @param pass, the password of the user
	 * @return a true or false depending on the user name and password entered
	 */
	public static boolean readTextFile(String username, String pass) {

		sc = null;
		try 
		{
			sc = new Scanner(new File("./dist/login.txt"));

			String cred = "";

			while(sc.hasNextLine())
			{
				cred = sc.nextLine();

				String [] strArr = cred.split(" ");

				if(strArr[0].equals(username) && strArr[1].equals(pass))
				{
					return true;
				}

			}
			return false;

		}catch(Exception ex)
		{
			System.err.println(ex.getMessage());
		}finally 
		{
			if(sc != null)
			{
				sc.close();
			}
		}
		return false;
	}


	/**
	 * Saves the graph in a binary file
	 */
	/**
	 * @param 
	 */
	/**
	 * @param graph the current existing graph
	 * @param file, the file that data is saved on
	 * @return true or false based on whether the data has been saved or not
	 */
	public static boolean saveAll(Graph<Town> graph, File file)
	{
		ObjectOutputStream os =null;

		try {
			FileOutputStream fos = new FileOutputStream(file);

			BufferedOutputStream bos = new BufferedOutputStream(fos);

			os = new ObjectOutputStream(bos);

			os.writeObject(graph);


		} catch (Exception e) {

			System.err.println(e.getMessage());
			return false;
		}finally {

			if(os != null)
			{
				try {
					os.close();
					return true;//for success
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		return false;

	}
	/**
	 * @param file, file read from
	 * @return the graph instance
	 */
	@SuppressWarnings("unchecked")
	public static Graph<Town> readAll(File file)
	{
		ObjectInputStream os =null;
		Graph<Town> graph = null;

		try {
			FileInputStream fis = new FileInputStream(file);

			BufferedInputStream bis = new BufferedInputStream(fis);

			os = new ObjectInputStream(bis);

			Object object = os.readObject();
			
			if(object instanceof Graph)
			{
				graph = (Graph<Town>)object;
			}

			
		} catch (Exception e) {

			System.err.println(e.getMessage());
			return null;
		}finally {

			if(os != null)
			{
				try {
					os.close();
					return graph;// success
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		return null;

	}

}
