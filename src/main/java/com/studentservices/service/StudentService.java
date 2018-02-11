package com.studentservices.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.studentservices.model.Course;
import com.studentservices.model.Student;

@Component
public class StudentService {

	private static List<Student> students = new ArrayList<Student>();
	//Static block begins
	static{
		
		Course course1 = new Course("Course1","Spring","10 Steps",Arrays.asList("Learn Maven","Import Maven Project"));
		Course course2 = new Course("Course2","Struts","2 Steps",Arrays.asList("Learn Struts","Import Struts Project"));
		Course course3 = new Course("Course3","OCAJP","4 Steps",Arrays.asList("Read Mala Gupta","Solve practice Tests"));
		Course course4 = new Course("Course4","OCPJP","4 Steps",Arrays.asList("Read OCPJP Books","Solve practice Tests"));

		Student ram = new Student("Student1","Ramanujan","SSE",Arrays.asList(course1,course2));
		Student laxman = new Student("Student 2", "Laxman", "TA", Arrays.asList(course3));
		
		students.add(ram);
		students.add(laxman);
		
	}
	
	public List<Student> retrieveAllStudents(){
		return students;
	}
	
	public Student retrieveStudent(String studentId){
		for(Student student : students){
			if(student.getId().equalsIgnoreCase(studentId)){
				return student;
			}
		}
		return null;
	}
	
	public List<Course> retrieveCourses(String studentId){
		Student student = retrieveStudent(studentId);
		
		if(student == null){
			return null;
		}
		return student.getCourses();
	}
	
	public Course retrieveCourse(String studentId,String courseId){
		Student student = retrieveStudent(studentId);
		
		if(null==student){
			return null;
		}
		for(Course course : student.getCourses()){
			if(course.getId().equalsIgnoreCase(courseId)){
				return course;
			}
			
		}
		return null;
	}
	
	private SecureRandom random = new SecureRandom();
	
	public Course addCourse(String studentId,Course course){
		Student student = retrieveStudent(studentId);
		
		if(student == null){
			return null;
		}
		
		String randomId = new BigInteger(130, random).toString(32);
		
		course.setId(randomId);
		student.getCourses().add(course);
		return course;
	}
}
