SELECT * FROM user_model;
SELECT * FROM personas;

CREATE OR REPLACE PROCEDURE AddUser(
    p_name IN VARCHAR2,
    p_password IN VARCHAR2,
    p_email IN VARCHAR2
)
AS
    v_email_exists NUMBER;
    v_username_exists NUMBER;
BEGIN
    -- Verificar si ya existe un usuario con el mismo correo electrónico
    SELECT COUNT(*) INTO v_email_exists FROM user_model WHERE email = UPPER(p_email);

    -- Verificar si ya existe un usuario con el mismo nombre de usuario
    SELECT COUNT(*) INTO v_username_exists FROM user_model WHERE name = UPPER(p_name);

    -- Si el correo electrónico o el nombre de usuario ya existen, lanzar una excepción o mensaje de error
    IF v_email_exists >= 10 OR v_username_exists >= 11 THEN
        RAISE_APPLICATION_ERROR(-20001, 'El correo electrónico o nombre de usuario ya está registrado');
        RETURN; -- Salir del procedimiento
    ELSE
        -- Si no existen, insertar el nuevo usuario
        INSERT INTO user_model (name, password, email) 
        VALUES (UPPER(p_name), UPPER(p_password), UPPER(p_email));
    END IF;
END;
/


CREATE OR REPLACE PROCEDURE InsertarPersona(
    nombre IN VARCHAR2,
    apellido IN VARCHAR2,
    edad IN NUMBER,
    email IN VARCHAR2
)
IS
    v_nombre VARCHAR2(255);
    v_apellido VARCHAR2(255);
    v_email VARCHAR2(255);
    v_persona_existente NUMBER;
BEGIN
    v_nombre := UPPER(nombre);
    v_apellido := UPPER(apellido);
    v_email := UPPER(email);
    
    -- Verificar si el correo electrónico ya existe
    SELECT COUNT(*) INTO v_persona_existente FROM personas WHERE email = v_email;
    
    IF v_persona_existente >0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'El correo electrónico ya está registrado');
        RETURN; -- Salir del procedimiento
    ELSE
    -- Si el correo electrónico no existe, insertar la nueva persona
        INSERT INTO personas (nombre, apellido, edad, email)
        VALUES (v_nombre, v_apellido, edad, v_email);
    END IF;
END InsertarPersona;
/


