INSERT INTO user_model (name, password, email)
VALUES ('PRUEBA1', '$2y$10$6mfP4jYg/AuoiSY7uBn4neejt4QFFV241xfleJpT5.sYAVDMcoHga', 'prueba1@NEORIS.COM');

SELECT * FROM user_model;

COMMIT;


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
    
    -- Verificar si el correo electr?nico ya existe
    SELECT COUNT(*) INTO v_persona_existente FROM personas WHERE email = v_email;
    
    IF v_persona_existente >0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'El correo electr?nico ya est? registrado');
        RETURN; -- Salir del procedimiento
    ELSE
    -- Si el correo electr?nico no existe, insertar la nueva persona
        INSERT INTO personas (nombre, apellido, edad, email)
        VALUES (v_nombre, v_apellido, edad, v_email);
    END IF;
END InsertarPersona;