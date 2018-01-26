update "user"
    SET password = MD5('haslo')
    WHERE id = 2;