CREATE DATABASE NasaImages
GO
USE NasaImages
GO

/*MAIN STRUCTURE*/

CREATE TABLE Photographer (
	IDPhotographer INT PRIMARY KEY IDENTITY,
	FirstName NVARCHAR(250),
	LastName NVARCHAR(300),

)

CREATE TABLE Writer (
	IDWriter INT PRIMARY KEY IDENTITY,
	FirstName NVARCHAR(250),
	LastName NVARCHAR(300),
)

CREATE TABLE Category (
	IDCategory INT PRIMARY KEY IDENTITY,
	Name NVARCHAR(150)
)

CREATE TABLE [Image]
(
	IDImage INT PRIMARY KEY IDENTITY,
	Title NVARCHAR(300),
	Link NVARCHAR(300),
	Description NVARCHAR(MAX),
	PicturePath NVARCHAR(90),
	PublishedDate NVARCHAR(90),
	PhotographerID INT NULL FOREIGN KEY REFERENCES Photographer(IDPhotographer),
	WriterID INT NULL FOREIGN KEY REFERENCES Writer(IDWriter),
	CategoryID INT NULL FOREIGN KEY REFERENCES Category(IDCategory)
)

GO

CREATE TABLE UserRole (
	IDUserRole INT PRIMARY KEY,
	Name NVARCHAR(50),
)

CREATE TABLE [User] (
	IDUser INT PRIMARY KEY IDENTITY,
	UserName NVARCHAR(30) NOT NULL UNIQUE,
	Password NVARCHAR(50),
	UserRoleID INT NOT NULL FOREIGN KEY REFERENCES UserRole(IDUserRole)
)
GO

/*PREDEFINED ROLES*/

INSERT INTO UserRole(IDUserRole, Name) VALUES (1, 'User')
INSERT INTO UserRole(IDUserRole, Name) VALUES (2, 'Admin')

INSERT INTO [User](UserName, Password, UserRoleID) VALUES ('admin', 'admin', 2)
GO


/*IMAGE CRUD*/

CREATE PROCEDURE createImage
	@Title NVARCHAR(300),
	@Link NVARCHAR(300),
	@Description NVARCHAR(MAX),
	@PicturePath NVARCHAR(90),
	@PublishedDate NVARCHAR(90),
	@IDImage INT OUTPUT
AS 
BEGIN 
	INSERT INTO Image(Title, Link, Description, PicturePath, PublishedDate) VALUES(@Title, @Link, @Description, @PicturePath, @PublishedDate)
	SET @IDImage = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateImage
	@Title NVARCHAR(300),
	@Link NVARCHAR(300),
	@Description NVARCHAR(MAX),
	@PicturePath NVARCHAR(90),
	@PublishedDate NVARCHAR(90),
	@IDImage INT
	 
AS 
BEGIN 
	UPDATE Image SET 
		Title = @Title,
		Link = @Link,
		Description = @Description,
		PicturePath = @PicturePath,
		PublishedDate = @PublishedDate		
	WHERE 
		IDImage = @IDImage
END
GO


CREATE PROCEDURE deleteImage
	@IDImage INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Image
	WHERE 
		IDImage = @IDImage
END
GO

CREATE PROCEDURE selectImage
	@IDImage INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Image
	WHERE 
		IDImage = @IDImage
END
GO

CREATE PROCEDURE selectImages
AS 
BEGIN 
	SELECT * FROM Image
END
GO

CREATE PROCEDURE setImagePhotographer
	@IDPhotographer INT,
	@IDImage INT
AS
BEGIN
	UPDATE Image SET 
		PhotographerID = @IDPhotographer
	WHERE 
		IDImage = @IDImage
END
GO

CREATE PROCEDURE setImageWriter
	@IDWriter INT,
	@IDImage INT
AS
BEGIN
	UPDATE Image SET 
		WriterID = @IDWriter
	WHERE 
		IDImage = @IDImage
END
GO

CREATE PROCEDURE setImageCategory
	@IDCategory INT,
	@IDImage INT
AS
BEGIN
	UPDATE Image SET 
		CategoryID = @IDCategory
	WHERE 
		IDImage = @IDImage
END
GO

/*PHOTOGRAPHER CRUD*/

CREATE PROCEDURE createPhotographer
	@FirstName NVARCHAR(250),
	@LastName NVARCHAR(300),
	@IDPhotographer INT OUTPUT
AS 
BEGIN 
	INSERT INTO Photographer VALUES(@FirstName, @LastName)
	SET @IDPhotographer = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updatePhotographer
	@FirstName NVARCHAR(250),
	@LastName NVARCHAR(300),
	@IDPhotographer INT
	 
AS 
BEGIN 
	UPDATE Photographer SET 
		FirstName = @FirstName,
		LastName = @LastName
	WHERE 
		IDPhotographer = @IDPhotographer
END
GO


CREATE PROCEDURE deletePhotographer
	@IDPhotographer INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Photographer
	WHERE 
		IDPhotographer = @IDPhotographer
END
GO

CREATE PROCEDURE selectPhotographer
	@IDPhotographer INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Photographer
	WHERE 
		IDPhotographer = @IDPhotographer
END
GO

CREATE PROCEDURE selectPhotographers
AS 
BEGIN 
	SELECT * FROM Photographer
END
GO

/*WRITER CRUD*/

CREATE PROCEDURE createWriter
	@FirstName NVARCHAR(250),
	@LastName NVARCHAR(300),
	@IDWriter INT OUTPUT
AS 
BEGIN 
	INSERT INTO Writer VALUES(@FirstName, @LastName)
	SET @IDWriter = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateWriter
	@FirstName NVARCHAR(250),
	@LastName NVARCHAR(300),
	@IDWriter INT
	 
AS 
BEGIN 
	UPDATE Writer SET 
		FirstName = @FirstName,
		LastName = @LastName
	WHERE 
		IDWriter = @IDWriter
END
GO


CREATE PROCEDURE deleteWriter
	@IDWriter INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Writer
	WHERE 
		IDWriter = @IDWriter
END
GO

CREATE PROCEDURE selectWriter
	@IDWriter INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Writer
	WHERE 
		IDWriter = @IDWriter
END
GO

CREATE PROCEDURE selectWriters
AS 
BEGIN 
	SELECT * FROM Writer
END
GO

/*CATEGORY CRUD*/
CREATE PROCEDURE createCategory
	@Name NVARCHAR(150),
	@IDCategory INT OUTPUT
AS 
BEGIN 
	INSERT INTO Category VALUES(@Name)
	SET @IDCategory = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateCategory
	@Name NVARCHAR(150),
	@IDCategory INT
	 
AS 
BEGIN 
	UPDATE Category SET 
		Name = @Name
	WHERE 
		IDCategory = @IDCategory
END
GO


CREATE PROCEDURE deleteCategory
	@IDCategory INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Category
	WHERE 
		IDCategory = @IDCategory
END
GO

CREATE PROCEDURE selectCategory
	@IDCategory INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Category
	WHERE 
		IDCategory = @IDCategory
END
GO

CREATE PROCEDURE selectCategories
AS 
BEGIN 
	SELECT * FROM Category
END
GO

/*USER CRUD*/

CREATE PROCEDURE createUser
	@UserName NVARCHAR(30),
	@Password NVARCHAR(50),
	@IDUser INT OUTPUT
AS 
BEGIN 
	INSERT INTO [User](UserName, Password, UserRoleID) VALUES(@UserName, @Password, 1)
	SET @IDUser = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE deleteUser
	@IDUser INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			[User]
	WHERE 
		IDUser = @IDUser
END
GO

CREATE PROCEDURE selectUser
	@IDUser INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		[User]
	WHERE 
		IDUser = @IDUser
END
GO

CREATE PROCEDURE selectUsers

AS
BEGIN
	SELECT * FROM [User]

END
GO

CREATE PROCEDURE findUser
	@UserName NVARCHAR(30),
	@Password NVARCHAR(50),
	@IDUser INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;
    
    SET @IDUser = NULL;
	SELECT @IDUser = IDUser FROM [User] WHERE UserName = @UserName AND Password = @Password
END
GO

/*DELETE ALL DATA*/
CREATE PROCEDURE DeleteAllData
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRANSACTION

        DELETE FROM [Image]
        DELETE FROM [User]

        DELETE FROM Photographer
        DELETE FROM Writer
        DELETE FROM Category

        COMMIT TRANSACTION
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK TRANSACTION
        
        DECLARE @ErrorMessage NVARCHAR(4000) = ERROR_MESSAGE()
        DECLARE @ErrorSeverity INT = ERROR_SEVERITY()
        DECLARE @ErrorState INT = ERROR_STATE()
        
        RAISERROR(@ErrorMessage, @ErrorSeverity, @ErrorState)
    END CATCH
END
GO