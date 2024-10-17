$(document).ready(function(){

    buscarEmpleado();
    aceptarBusquedaEmpleado();
    
    registrarUsuario();
});

function buscarEmpleado(){
    $('#btnBuscarEmpleado').click(function(){
        localStorage.removeItem('empleado');
        $('#resultadoBusquedaEmpleado').empty();
        $('#formularioRegistro').css('display', 'none');
        var legajo = $('#inputLegajo').val();
        

        if(legajo === ''){
            console.log("no hay legajo");
            $('#resultadoBusquedaEmpleado').append('<p>Ingrese un numero de legajo</p>');
        }
        else{
            console.log(legajo);
            $.ajax({
                    url: 'SvResultadoBuscarLegajo', // URL del servlet
                    type: 'POST',
                    data: {legajo: legajo},
                    dataType: 'json',
                    success: function(response) {
                        // recibo un json con clave "empleado"
                        // valor = null para empleado inexistente
                        // valor = false para empleado con usuario ya creado
                        // valor = empleado para empleado sin usuario
                        if(response.empleado === null){
                            $('#resultadoBusquedaEmpleado').append('<p>No existe empleado con ese legajo</p>');
                        }
                        else if(response.empleado === false){
                            $('#resultadoBusquedaEmpleado').append('<p>El empleado ingresado ya tiene un usuario</p>');
                        }
                        else{
                            $('#resultadoBusquedaEmpleado').append(
                               '<p> Usted es: '+response.apellidos+', '+response.nombres+'</p>'+
                               '<p> Cargo: '+response.jerarquia.descripcion+'</p>'+
                               '<p id="legajoEmpleado" value="'+response.legajo+'"> Numero de legajo: '+response.legajo+'</p>'+
                               '<p> Si estos datos son correctos, presione el boton "Aceptar", de lo contrario, ingrese nuevamente su legajo</p>'+
                               '<button id="aceptarBusquedaEmpleado">Aceptar</button>'
                            );
                            console.log(response);
                            localStorage.setItem('empleado', JSON.stringify(response));
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error("Error:", error);
                        $('#resultadoBusquedaEmpleado').append('<p>Ocurrio un error al buscar el legajo</p>');
                        $('#formularioRegistro').css('display', 'none');
                    }
            });
        }
    });

}


function aceptarBusquedaEmpleado(){
    $('#resultadoBusquedaEmpleado').on('click', '#aceptarBusquedaEmpleado', function(){
        cargarRoles();
        $('#formularioRegistro').css('display', 'block');
    });
}

function cargarRoles(){
    $.ajax({
            url: 'SvResultadoBuscarLegajo', // URL del servlet
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                // 'data' es un array de objetos Usuario en formato JSON
                // Aquí puedes iterar y mostrar los datos en tu página
                $.each(data, function(i, item){
                    $('#rol').append('<option value="'+item.id_rol+'" id="'+item.descripcion+'">'+item.descripcion+'</option>');
                });   
            },
            error: function(xhr, status, error) {
                console.error('Error al obtener la lista de roles:', error);
            }
        });
}


function registrarUsuario(){
    //$('#registrar').on('click', function(){
        var usuarioValido = '';
        var claveValida = '';
        var verificarUsuario = false;
        var verificarClave = false;
        var verificarRepetirClave = false;
        
        //Validar nombre
        const erUsuario = /^[a-z0-9_-]{3,16}$/;
        //Validar password
        const erPassword = /^[a-z0-9_-]{6,18}$/;
        
        $('#usuario').on('input', function(){
            // verificar el usuario
            var usuario = $('#usuario').val();
            if(erUsuario.test(usuario) && usuario.length >= 8){
                console.log(usuario);
                $.ajax({
                    url: 'SvRegistrar',
                    type: 'GET',
                    data: { username: usuario },
                    success: function(response) {
                        if (response.existe) {
                            // true, existe el usuario
                            $('#mensajeUsuario').text('El usuario ya existe. Elige otro nombre.');
                            $('#mensajeUsuario').css('color', 'red');
                            verificarUsuario = false;
                        } else {
                            // false, no existe
                            $('#mensajeUsuario').text('Usuario Valido');
                            $('#mensajeUsuario').css('color', 'green');
                            verificarUsuario = true;
                            usuarioValido = usuario;
                        }
                    },
                    error: function() {
                        $('#mensajeUsuario').text('Error al verificar el usuario.');
                        verificarUsuario = false;
                    }
                });
            }
            else{
                $('#mensajeUsuario').text('Usuario incorrecto, debe tener al menos 8 caracteres, sin espacios');
                $('#mensajeUsuario').css('color', 'red');
                verificarUsuario = false;
            }
        });
        

        $('#clave').on('input', function(){
            var clave = $('#clave').val();
            console.log(clave);
            if(erPassword.test(clave) && clave.length >= 8){
                $('#mensajeClave').text('Clave valida');
                $('#mensajeClave').css('color', 'green');
                verificarClave = true;
            }
            else{
                $('#mensajeClave').text('Clave incorrecta, debe tener al menos 8 caracteres, sin espacios');
                $('#mensajeClave').css('color', 'red');
                verificarClave = false;
            }
        });


        $('#repetirClave').on('input', function(){
            // verificar repetir clave
            var clave = $('#clave').val();
            var repetirClave = $('#repetirClave').val();
            console.log(repetirClave);
            if(erPassword.test(clave) && clave.length >= 8 && clave === repetirClave){
                $('#mensajeRepetirClave').text('Clave valida');
                $('#mensajeRepetirClave').css('color', 'green');
                verificarRepetirClave = true;
                claveValida = repetirClave;
            }
            else if(erPassword.test(clave) && clave.length >= 8 && clave !== repetirClave){
                $('#mensajeRepetirClave').text('Las claves ingresadas no coinciden');
                $('#mensajeRepetirClave').css('color', 'red');
                verificarRepetirClave = false;
            }
            else{
                $('#mensajeRepetirClave').text('Clave incorrecta, debe tener al menos 8 caracteres');
                $('#mensajeRepetirClave').css('color', 'red');
                verificarRepetirClave = false;
            }
        });

    $('#registrar').click(function(){
        if(verificarUsuario === true && verificarClave === true && verificarRepetirClave === true){
            $('#mensajeCompletar').empty();
            // capturo el id y la descripcion del rol de usuario seleccionado
            var idRol = $('#rol').val();
            var descripcionRol = $('#rol option:selected').html();
            // crar objeto rol
            var rol ={
                id_rol: idRol,
                descripcion: descripcionRol
            };
            
            var recuperarEmpleado = localStorage.getItem('empleado');
            console.log(recuperarEmpleado);

            var empleado = JSON.parse(recuperarEmpleado);
            console.log(empleado);
            // crear objeto usuario, compuesto de un objeto empleado y objeto rol
            var nuevoUsuario = {
                empleado: empleado,
                usuario: usuarioValido,
                clave: claveValida,
                rol: rol,
                auditoria: '',
                aprobado: false
            };
            // envio el objeto usuario a la funcion
            crearUsuario(nuevoUsuario);
            //redirigirIndex();
            $('#mensajeCompletar').text('Campos validos');
            $('#mensajeCompletar').css('color', 'green');
        }
        else{
            $('#mensajeCompletar').text('Complete los campos de usuario y clave para poder registrarse');
            $('#mensajeCompletar').css('color', 'red');
        }
    });
}

// recibo el objeto empleado para enviarlo al servlet con ajax
// para evitar una nueva consulta a la base de datos en el servlet

function crearUsuario(usuario){
    console.log('envio los datos al servlet para crear un usuario');
    console.log(usuario);
    $.ajax({
        url: 'SvRegistrar',
        type: 'POST',
        contentType: 'application/json; charset=utf-8', // especifico que es json
        data: JSON.stringify(usuario),
        success: function (response) {
            console.log(response);
            redirigirIndex();
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function redirigirIndex(){
        console.log('redirigir');
        
        //$('#contenidoRegistrar').css('display', 'none');
        
        $('#buscarEmpleado').css('display', 'none');
        $('#resultadoBusquedaEmpleado').css('display', 'none');
        $('#formularioRegistro').css('display', 'none');
        //$('#mensajeCreacion').css('display', 'block');
        
        $('#mensajeCreacion').append(
            '<p>El usuario fue creado con exito</p>'+
            '<button id="btnUsuarioCreado">Aceptar</button>'
        );

}
//recibo la respuesta listaRoles e inserto en la pestania desplegable