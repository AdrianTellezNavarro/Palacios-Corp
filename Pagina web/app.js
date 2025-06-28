
//declaramos una varible para ocupar el framework expres
const express = require("express")
const mysql= require("mysql2")

let bodyParser=require('body-parser')
let app=express()
let con=mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'n0m3l0',
    database:'tempsys'
})
con.connect();

//crud create,read,update, delete
app.use(bodyParser.json())

app.use(bodyParser.urlencoded({
    extended:true
}))
// en public esta todo el front
app.use(express.static('public'))


// Ruta para agregar un usuario
app.post('/agregarUsuario',(req,res)=>{
        let nombre=req.body.nombre
        let id=req.body.id

        con.query('INSERT INTO usuario (id_usuario, nombre) VALUES (?, ?)', [id, nombre], (err, respuesta, fields) => {
            if (err) {
                console.log("Error al conectar", err);
                return res.status(500).send("Error al conectar");
            }
           
            return res.send(`<h3>Usuario creado correctamente:</h3> ${nombre}
                <br><a href="/obtenerUsuario">Ver usuarios</a>`);
            
        });
})

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
    const { id, nombre } = req.body;

    if (!id || !nombre) {
        return res.status(400).send('Faltan datos para iniciar sesión');
    }

    con.query(
        'SELECT * FROM usuario WHERE id_usuario = ? AND nombre = ?',
        [id, nombre],
        (err, resultados) => {
            if (err) {
                console.error('Error en login:', err);
                return res.status(500).send('Error en el servidor');
            }
            if (resultados.length === 0) {
                return res.status(401).send('Usuario o nombre incorrecto');
            }
            // Aquí podrías crear una sesión, token, etc.
            res.send(`Bienvenido, ${resultados[0].nombre}!`);
        }
    );
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