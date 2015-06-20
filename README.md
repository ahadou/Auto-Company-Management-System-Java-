#A simple auto company management system created using the Java programming language. This program has its own GUI created using javax.swing and awt packages and classes. It also uses the JDBC driver to link it with an SQL database which is used to store data. The program shows many examples of the java basics along, abstract classes, inheritance, interfaces and much more. I did however first create this program using a local SQL database so if you choose to download this program it wont function without the database itself. You can simply create the database and tables needed, its not over complicated for someone with SQL knowledge and uses only simple datatypes such as int and varchar. The description of the database if you wish to create it is as follows:

DATABASE NAME: auto_company
TABLE NAMES: employees, planner, transactions.

EMPLOYEES TABLE DESCRIPTION:
FIELD: EmpID                TYPE: int(11)                   NULL: no      KEY: Primary      EXTRA:auto_increment
FIELD: EmployeeName        TYPE: varchar(40)                NULL: yes       KEY: -            EXTRA: -
FIELD: EmployeeAddress        TYPE: varchar(40)            NULL: yes       KEY: -     	     EXTRA: -
FIELD: EmployeePhone        TYPE: varchar(15)                NULL: yes     KEY: -    	       EXTRA: -
FIELD: EmployeeDOB        TYPE: varchar(14)                NULL: yes       KEY: -     	    EXTRA: -
FIELD: EmployeeJob        TYPE: varchar(25)                NULL: yes       KEY: -     	    EXTRA: -
FIELD: EmployeeSalary        TYPE: varchar(30)             NULL: yes        KEY: -     	      EXTRA: -


PLANNER TABLE DESCRIPTION:
FIELD: ID                 TYPE: int(11)              NULL: no         KEY: Primary      EXTRA:auto_increment
FIELD: Date              TYPE: varchar(15)            NULL: yes     KEY: -     		EXTRA: -
FIELD: Time             TYPE: varchar(15)            NULL: yes     KEY: -     		EXTRA: -
FIELD: Description        TYPE: varchar(200)          NULL: yes     KEY: -     		EXTRA: -

TRANSACTIONS TABLE DESCRIPTION:

FIELD: TransCustID            TYPE: int(11)              NULL: no      KEY: Primary      EXTRA:auto_increment
FIELD: CustomerName        TYPE: varchar(30)           NULL: no           KEY: -            EXTRA: -
FIELD: CustomerAddress        TYPE: varchar(50)            NULL: no     KEY: -     	     EXTRA: -
FIELD: CustomerPhone        TYPE: varchar(16)            NULL: no     	KEY: -    	     EXTRA: -
FIELD: CustomerDOB        TYPE: varchar(20)            NULL: no       	KEY: -     	      EXTRA: -
FIELD: InsuranceCompany        TYPE: varchar(30)        NULL: no       KEY: -     	    EXTRA: -
FIELD: NCB                TYPE: varchar(3)            NULL: no     	   KEY: -     	    EXTRA: -
FIELD: CarMake            TYPE: varchar(20)           NULL: no          KEY: -         EXTRA: -
FIELD: CarModel         TYPE: varchar(20)             NULL: no      	 KEY: -            EXTRA: -
FIELD: CarReg        TYPE: varchar(13)                 NULL: no     		KEY: -     	    EXTRA: -
FIELD: CarColor        TYPE: varchar(20)            NULL: no     	    KEY: -    	      EXTRA: -
FIELD: CarEngine         TYPE: varchar(10)            NULL: no       	KEY: -     	      EXTRA: -
FIELD: CarPrice         TYPE: varchar(12)            NULL: no       	KEY: -     	      EXTRA: -
FIELD: CarVAT         TYPE: varchar(7)               NULL: no     		KEY: -     	      EXTRA: -
FIELD: CarTotal         TYPE: varchar(12)            NULL: no       	KEY: -     	      EXTRA: -
FIELD: BuyOrSell        TYPE: varchar(10)            NULL: no     	   KEY: -     	      EXTRA: -
