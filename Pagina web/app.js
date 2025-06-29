
//declaramos una varible para ocupar el framework expres
const express = require("express")
const mysql= require("mysql2")

let bodyParser=require('body-parser')
let app=express()
let con=mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'n0m3l0',
    database:'bdweb'
})
con.connect();


app.use(bodyParser.json())

app.use(bodyParser.urlencoded({
    extended:true
}))
// en public esta todo el front
app.use(express.static('public'))


// Ruta para agregar un usuario
app.post('/agregarUsuario', (req, res) => {
    let usuario = req.body.usuario;
    let contraseña = req.body.contraseña;

    if (!usuario || !contraseña) {
        return res.status(400).send("Faltan datos: usuario o contraseña");
    }

    con.query('INSERT INTO usuarios (Usuario, Contraseña) VALUES (?, ?)', [usuario, contraseña], (err, respuesta) => {
        if (err) {
            console.log("Error al insertar en la base de datos:", err);
            return res.status(500).send("Error al conectar con la base de datos");
        }
        // Redirige al usuario a menu.html en la carpeta public
       res.send("Usuario agregado correctamente");
    });
});


//Función para consultar todos los usuarios

app.get('/obtenerUsuario', (req, res) => {
    con.query('SELECT * FROM usuario', (err, respuesta, fields) => {
        if (err) {
            console.log('ERROR: ', err);
            return res.status(500).send('Error al obtener usuarios');
        }

        var userHTML = '';
        respuesta.forEach(user => {

            userHTML += `<tr><td>${user.id_usuario}</td><td>${user.nombre}</td></tr>`;
        });

        res.send(`
            <!DOCTYPE html>
            <html lang="en">
            <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
            <style>
            h3{
                text-align: center;
                color: #333;
                font-family: Arial, sans-serif;
            }
        table {
            width: 60%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: transparent;
            color: #000;
            font-family: Arial, sans-serif;
        }

        th, td {
            border: 3px solid black;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: rgba(0, 0, 0, 0.05);
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: rgba(0, 0, 0, 0.02);
        }
            body {
                background-color:rgb(143, 153, 211);}
        </style>
    </head>
    <body>
            
        <h3>Lista de Usuarios:</h3>
        <br>
        <table>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
            </tr>
            ${userHTML}
        </table>
    </body>
</html>
        `);
    });
});


app.post('/login', (req, res) => {
    const usuario = req.body.usuario;
    const contraseña = req.body.contraseña;

    if (!usuario || !contraseña) {
        return res.status(400).send("Faltan datos");
    }

    const sql = 'SELECT * FROM usuarios WHERE Usuario = ? AND Contraseña = ?';
    con.query(sql, [usuario, contraseña], (err, resultados) => {
        if (err) {
            console.error("Error al hacer login:", err);
            return res.status(500).send("Error en el servidor");
        }

        if (resultados.length > 0) {
            // Inicio de sesión exitoso
            res.redirect('/menu.html'); // o puedes usar res.send("Bienvenido")
        } else {
            // Usuario o contraseña incorrectos
            res.status(401).send("Usuario o contraseña incorrectos");
        }
    });
});


app.post('/editarUsuario', (req, res) => {
    const id = req.body.id;
    const nuevoNombre = req.body.nuevoNombre;

    con.query('UPDATE usuario SET nombre = ? WHERE id_usuario = ?', [nuevoNombre, id], (err, resultado) => {
        if (err) return res.status(500).send('Error al actualizar usuario');
        if (resultado.affectedRows === 0) return res.send('Usuario no encontrado');
        res.send(`✅ Usuario con ID ${id} actualizado a: ${nuevoNombre}`);
    });
});

app.post('/eliminarUsuario', (req, res) => {
    const id = req.body.id;

    con.query('DELETE FROM usuario WHERE id_usuario = ?', [id], (err, resultado) => {
        if (err) return res.status(500).send('Error al eliminar usuario');
        if (resultado.affectedRows === 0) return res.send('Usuario no encontrado');
        res.send(`❌ Usuario con ID ${id} eliminado correctamente.`);
    });
});
const PORT = 1000;

app.listen(PORT, () => {

  console.log(`🚀 Servidor funcionando en http://localhost:${PORT}`);

});