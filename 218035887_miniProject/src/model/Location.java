package model;

public class Location implements Comparable<Location>, java.io.Serializable{
	

	    private static final long serialVersionUID = 1L;
		private  String name;

	        public void setName(String name) {
				this.name = name;
			}

			public Location(String name) 
	        {
	            this.name = name;
	        }

	        @Override
	        public String toString()
	        {
	            return name;
	        }
	        
	        public String getName() {
				return name;
			}

			@Override
			public int compareTo(Location o) {
				if(o.getName().equals(this.getName()))
				{
					return 0;
				} else {
					return -1;
				}
			}

}
