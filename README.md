

<body>
    <h1>Library Management System API
    </h1>
    <p> Gradle version 8.5 </p>
    <p> JAVA version 17.0.6</p>
    <div>
        <h2>Integrate API</h2>
        <p><b>Git clone https://github.com/zoalfikar/library_managment_system.git</b></p>
        <p><b>Cd library_managment_system</b></p>
        <p><b>Gradle build</b> &nbsp;&nbsp;or &nbsp;&nbsp;<b>.\ gradlew build </b> </p>
    </div>
    <div>
        <h2>Setup</h2>
        <h4>Connecting with Mysql</h4>
        <p>Run Mysql server <b> net start mysql </b> &nbsp;&nbsp;or&nbsp;&nbsp; <b>net start mysql80 </b> according to your Mysql version Create new database .
        </p>
        <p>Set up the application.properties add [ datasource.username and datasource.password and database.name] .
        </p>
        <p> <b>gradle :utilities:run --args=migrate</b>&nbsp; &nbsp; or &nbsp; &nbsp; <b>gradle :utilities:run --args=migrate:refresh</b>
        </p>
        <p>to Create a migration Go to utilities\src\main\java\migrations\tables add file {migration_name}.migration .
        </p>
        <p><b>gradle :utilities:run --args=migrate:triggers
            </b></p>
        <p><b>gradle :utilities:run --args=seed:library
            </b></p>
    </div>
    <div>
        <h2>start the application</h2>
        <p><b>Gradle bootrun
            </b></p>
    </div>
    <div>
        <h2>Testing th Api</h2>
        <p><b>Gradle test
            </b></p>
    </div>
    <div>
        <h2>Useage</h2>
        <p><b>curl http://localhost:7788
            </b></p>
        <p>{"message":"Welcome in library managment system"}
        </p>
    </div>
    <div>
        <h2>APIs</h2>
        <h4>CURD Patron</h4>
        <p> get <i> api/patrons </i> all patrons
        </p>
        <p> get <i> api/patrons/{patronId} </i> retrieve patron
        </p>
        <p> post <i> api/patrons </i> add new patron
        </p>
        <p> put <i> api/patrons/{patronId} </i> update patron
        </p>
        <p> delete <i> api/patrons/{patronId} </i> delete patron
        </p>
        <h4>CURD Book</h4>
        <p> get <i> api/books </i> all books
        </p>
        <p> get <i> api/books/{bookId} </i> retrieve book
        </p>
        <p> post <i> api/books </i> add new book
        </p>
        <p> put <i> api/books/{bookId} </i> update book
        </p>
        <p> delete <i> api/books/{bookId} </i> delete book
        </p>
        <h4>Library Managment</h4>
        <p> post <i> /api/borrow/{bookId}/patron/{patronId} </i> patron borrow a book
        </p>
        <p> put <i>  /api/return/{bookId}/patron/{patronId} </i> patron return a book
        </p>
        <p> Get <i> /api/borrowing/records/patron/{patronId} </i> get patron borrowing records
        </p>
        <p> Get <i> /api/borrowing/records/book/{bookId}     </i> get book borrowing records
        </p>
    </div>
</body>

</html>
