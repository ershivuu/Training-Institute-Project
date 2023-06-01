package com.genpact.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import com.genpact.dao.InstituteDAO;
import com.genpact.pojo.Student;
import com.genpact.pojo.Course;
import com.genpact.pojo.Feedback;
import com.genpact.pojo.Institute;
import com.genpact.pojo.Request;


@Service
public class InstituteDAOImpl implements InstituteDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public boolean updateInstitute(Institute institute) {
		
		String query = "UPDATE institute SET instituteName = ? , "
				+ "affiliationDate = ? , address = ?, seats = ? "
				+ "WHERE instituteID = ?";
		
		try {
			
			int count = jdbcTemplate.update(query, new
					 Object[] {institute.getInstituteName() , 
			institute.getAffiliationDate() , 
			institute.getAddress() , institute.getSeats() , 
			institute.getInstituteID()});
			
			if(count > 0)
				return true;
			else
				return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean deleteInstitute(int instituteID) {
		
		String query = "DELETE FROM institute WHERE instituteID = ?";
		
		try {
			
			int count = jdbcTemplate.update(query, new
					 Object[] {instituteID});
			
			if(count > 0)
				return true;
			else
				return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean addNewInstitute(Institute institute) {
		
		String query = "INSERT INTO institute(instituteName, "
+ "institutePassword, affiliationDate,  "
+ "address, seats) VALUES(?,?,?,?,?)";
		
		try {
			int count = jdbcTemplate.update(query, new
					 Object[] {institute.getInstituteName() , 
			institute.getInstitutePassword() ,
			institute.getAffiliationDate(),
			institute.getAddress() , institute.getSeats()});
				
			if(count > 0)
				return true;
			else
				return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean checkInstitute(Institute institute) {
		
		String query = "SELECT * FROM institute WHERE instituteID = ? "
				+ "and institutePassword = ?";
		
		try {
			Institute ref = jdbcTemplate.queryForObject(query, 
			new BeanPropertyRowMapper<Institute>(Institute.class),
			new Object[] {institute.getInstituteID() , 
					institute.getInstitutePassword()});
			
			if(ref != null)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public List<Institute> getAllInstitutes() {
		
		List<Institute> lst = new ArrayList<>();
        
		String query = "SELECT instituteID, instituteName, "
+ "affiliationDate, address, seats, filledseats FROM institute";
		
		try {
		
		lst = jdbcTemplate.query(query, 
			new BeanPropertyRowMapper<Institute>(Institute.class));

		return lst;
		
		} catch (Exception e) {
			e.printStackTrace();
			lst.clear();
			return lst;
		}
	}
	
	public Institute getInstituteDetails(int instituteID)
	{
		String query = "SELECT instituteID, instituteName, "
+ "affiliationDate, address, seats, filledseats "
+ "FROM institute WHERE instituteID = ?";
		
		try {
			Institute ref = jdbcTemplate.queryForObject(query, 
					new BeanPropertyRowMapper<Institute>(
							Institute.class),
					new Object[] {instituteID});
			
			if(ref != null)
				return ref;
			else
				return new Institute();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return new Institute();
		}
}

	@Override
	public List<Request> getAllRequests(int courseID) {
		
		List<Request> lstList = new ArrayList<Request>();
		
		int instituteID = 0;
        
        String query = "SELECT instituteID FROM course WHERE "
        		+ "courseID = ?";
        
        try {
			instituteID = jdbcTemplate.execute(query, 
					new PreparedStatementCallback<Integer>() {
				@Override
				public Integer doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {
					ps.setInt(1, courseID);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
						return rs.getInt("instituteID");
					else return 0;
				}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
        
			try {
					lstList = 
					jdbcTemplate.query(
					"SELECT * FROM admissionrequest WHERE"
					+ " courseID IN (SELECT courseID FROM course WHERE"
					+ " instituteID = ?)",
					new BeanPropertyRowMapper<Request>(Request.class),
					instituteID);

					return lstList;
				}
		catch(Exception ex) {
			ex.printStackTrace();
			lstList.clear();
			return lstList;
		}
	}
	

	@Override
	public List<Student> getAllStudents(int instituteID) {
			
		List<Student> lstList = new ArrayList<Student>();
		List<String> idList = new ArrayList<>();
		final List<String> mylist = idList;
		
			String query = "SELECT studentID FROM stud_institute"
					+ " WHERE instituteID = ?";
			
			idList = jdbcTemplate.execute(query, 
					new PreparedStatementCallback<List<String>>() {
				@Override
				public List<String> doInPreparedStatement(
						PreparedStatement ps)
						throws SQLException, DataAccessException {
					
					List<String> lst = new ArrayList<>();
					
					ps.setInt(1, instituteID);
					ResultSet rs = ps.executeQuery(); 
					
					while(rs.next()) {
						String id = rs.getString("studentID");
						lst.add(id);
					}
					
					return lst;
				}
					});
			
			 query = "SELECT * FROM student WHERE userID = ?";
			 
			 lstList = jdbcTemplate.execute(query, 
			new PreparedStatementCallback<List<Student>>() {
				 
				 @Override
				public List<Student> doInPreparedStatement(
						PreparedStatement ps)
						throws SQLException, DataAccessException {
					
					List<Student> ls = new ArrayList<>();
					
					if(mylist.size() > 0) {
						for(String id : mylist) {
							ps.setString(1, id);
							ResultSet rs = ps.executeQuery();
							if(rs.next()) {
								Student s1 = new Student();
								s1.setUserID(rs.getString("userID"));
								s1.setStudentName(rs.getString("studentName"));
								s1.setEmailID(rs.getString("emailID"));
								s1.setNumber(rs.getString("number"));
								ls.add(s1);
							}
						}
					}
					return ls;
				}
			});
			 
			 return lstList;
		}


	@Override
	public boolean setStudentRequestStatus(Request studentRequest,
			int status) {
		boolean b = false;
        String query = "UPDATE admissionrequest SET status = ? "
+ "WHERE userID = ? AND courseID = ?";
        try {
            int count = jdbcTemplate.execute(query, 
            		new PreparedStatementCallback<Integer>() {
                @Override
                public Integer doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                    ps.setInt(1, status);
                    ps.setString(2, studentRequest.getUserID());
                    ps.setInt(3, studentRequest.getCourseID());
                    return ps.executeUpdate();
                }
            });

            if (count > 0)
                b = true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return b;
	}


	@Override
	public List<Feedback> getAllFeedbacks(int instituteID) {
		List<Feedback> lstList = new ArrayList<Feedback>();
		
		String query = "SELECT * from feedback WHERE instituteID = ?";
		
		try {
			
			lstList = jdbcTemplate.query(query, 
			new BeanPropertyRowMapper<Feedback>(Feedback.class),
			instituteID);
			
			return lstList;
			
		} catch (Exception e) {
			e.printStackTrace();
			lstList.clear();
			return lstList;
		}
	}
	
	@Override
    public boolean isSeatAvailable(int courseID) {
        boolean b = false;
        int instituteID = 0;
        
        String query = "SELECT instituteID FROM course WHERE "
        		+ "courseID = ?";
        
        try {
			instituteID = jdbcTemplate.execute(query, 
					new PreparedStatementCallback<Integer>() {
				@Override
				public Integer doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {
					ps.setInt(1, courseID);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
						return rs.getInt("instituteID");
					else return 0;
				}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
        
         query = "SELECT seats, filledseats "
+ "FROM institute WHERE instituteID = ?";
         final int id = instituteID;
         
        try {
            b = jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {

                @Override
                public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();

                    if (rs.isBeforeFirst()) {
                        rs.next();
                        int totalSeats = rs.getInt("seats");
                        int filledSeats = rs.getInt("filledseats");
                        if (totalSeats > filledSeats) {
                            return true;
                        } else {
                            return false;
                        }
                    } else
                        return false;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return b;
    }
    
    @Override
    public boolean requireAttention(Request studentRequest) {
        boolean b = false;
        String query = "SELECT status FROM admissionrequest "
+ "WHERE userID = ? AND courseID = ?";
        
        try {
            b = jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {

                @Override
                public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                    ps.setString(1, studentRequest.getUserID());
                    ps.setInt(2, studentRequest.getCourseID());
                    ResultSet rs = ps.executeQuery();

                    if (rs.isBeforeFirst()) {
                        rs.next();
                        int status = rs.getInt("status");
                        
                        // Is Pending
                        if (status == 0) {
                            return true;
                        } else {
                            return false;
                        }
                    } else
                        return false;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return b;
    }

    
    
    @Override
    public boolean incrementFilledSeatByOne(int courseID) {
        boolean b = false;
        
        int instituteID = 0;
        
        String query = "SELECT instituteID FROM course WHERE "
        		+ "courseID = ?";
        
        try {
			instituteID = jdbcTemplate.execute(query, 
					new PreparedStatementCallback<Integer>() {
				@Override
				public Integer doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {
					ps.setInt(1, courseID);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
						return rs.getInt("instituteID");
					else return 0;
				}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
        
         query = "UPDATE institute SET filledseats = "
        		+ "filledseats + 1 WHERE instituteID = ?";
         final int id = instituteID;
         
        try {
            int count = jdbcTemplate.execute(query, new PreparedStatementCallback<Integer>() {
                @Override
                public Integer doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                    ps.setInt(1, id);
                    return ps.executeUpdate();
                }
            });

            if (count > 0)
                b = true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return b;
    }


	@Override
	public boolean updateStudentWithInstitute(
			String studentID, int instituteID) {
		String query = "INSERT INTO stud_institute "
				+ "VALUES(?,?)";
        
        try {
            int count = jdbcTemplate.update(query,
            		new Object[] {
            		studentID , instituteID}); 

            if (count > 0)
                return true;
            else
            	return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
    
    /*
    @Override
    public boolean updateStudentWithInstitute(int courseID) {
        
    	int instituteID = 0;
        
        String query = "SELECT instituteID FROM course WHERE "
        		+ "courseID = ?";
        
        try {
			instituteID = jdbcTemplate.execute(query, 
					new PreparedStatementCallback<Integer>() {
				@Override
				public Integer doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {
					ps.setInt(1, courseID);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
						return rs.getInt("instituteID");
					else return 0;
				}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        query = "INSERT INTO stud_institute VALUES(?,?)";
        
        try {
            int count = jdbcTemplate.update(query, new Object[] {
            		instituteID , courseID}); 

            if (count > 0)
                return true;
            else
            	return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
	*/
    
    
}








