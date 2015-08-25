package datamanagement;
        public class ListStudentsCTL 
        {
        	private StudentManager studMap;
        	public ListStudentsCTL() {studMap = StudentManager.getInstance();
        	}
        	
            public void listStudents( IStudentLister lister, String unitCode ) 
            {
            	lister.clearStudents();
            	StudentMap students = studMap.getStudentsByUnit( unitCode );
            	for (Integer id : students.keySet() ) lister.addStudent(students.get(id));
            	
            }
            
        }
