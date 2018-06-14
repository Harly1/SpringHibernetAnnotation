package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.configuration.HibernateConfiguration;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	private SessionFactory sessionFactory;
//			= (SessionFactory) new AnnotationConfigApplicationContext().getBean("sessionFactory");


	//	public Employee findById(int id) {
//		return getByKey(id);
//	}

		public Employee findById(int id) {
			return (Employee) sessionFactory.openSession().get(Employee.class, id);
		}

	public void saveEmployee(Employee employee) {
			Session session = sessionFactory.openSession();
			session.save(employee);
		}

		public void deleteEmployeeBySsn (String ssn){
			Query query = sessionFactory.openSession().createSQLQuery("delete from Employee where ssn = :ssn");
			query.setString("ssn", ssn);
			query.executeUpdate();
		}

		@SuppressWarnings("unchecked")
		public List<Employee> findAllEmployees () {
			List<Employee> employees = (List<Employee>) sessionFactory.openSession().createQuery("FROM Employee").list();
			return employees;
		}

		public Employee findEmployeeBySsn (String ssn){
//			Criteria criteria = createEntityCriteria();
//			criteria.add(Restrictions.eq("ssn", ssn));
//			return (Employee) criteria.uniqueResult();
			return null;
		}

}