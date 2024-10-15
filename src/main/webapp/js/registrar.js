$(document).ready(function(){
    
    buscarEmpleado();
    aceptarBusquedaEmpleado();
});

function cargarRoles(){
    console.log('############## cargarRoles()()');
    $.ajax({
            url: 'SvResultadoBuscarLegajo', // URL del servlet
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                // 'data' es un array de objetos Usuario en formato JSON
                // Aquí puedes iterar y mostrar los datos en tu página
                $.each(data, function(i, item){
                    //console.log(item);
                    $('#rol').append('<option value="'+item.id_rol+'" id="'+item.descripcion+'">'+item.descripcion+'</option>');
                });   
            },
            error: function(xhr, status, error) {
                console.error('Error al obtener la lista de roles:', error);
            }
        });
}

function buscarEmpleado(){
    $('#btnBuscarEmpleado').click(function(){
        console.log('############## buscarEmpleado()');
        $('#resultadoBusquedaEmpleado').empty();
        var buscarLegajo = $('#inputLegajo').val();
        
        if(buscarLegajo === ''){
            console.log("no hay legajo");
            $('#resultadoBusquedaEmpleado').append('<p>Ingrese un numero de legajo</p>');
        }
        else{
            $.ajax({
                    url: 'SvResultadoBuscarLegajo', // URL del servlet
                    type: 'POST',
                    data: {legajo: buscarLegajo},
                    dataType: 'json',
                    success: function(response) {
                        // 'response' es un array de objetos Usuario en formato JSON
                        //console.log(response);
                        $('#resultadoBusquedaEmpleado').append(
                                '<p> Usted es: '+response.apellidos+', '+response.nombres+'</p>'+
                                '<p> Cargo: '+response.jerarquia.descripcion+'</p>'+
                                '<p>Numero de legajo: '+response.legajo+'</p>'+
                                '<p>Si estos datos son correctos, presione el boton "Aceptar", de lo contrario, ingrese nuevamente su legajo</p>'+
                                '<button id="aceptarBusquedaEmpleado">Aceptar</button>'
                        );
                        // llamo a la funcion registroUsuario y le paso el response empleado como parametro
                        registrarUsuario(response);
                    },
                    error: function(xhr, status, error) {
                        //console.error('Error al obtener el legajo ingresado:', error);
                        console.log('error al obtener el empleado');
                        $('#resultadoBusquedaEmpleado').append('<p>No se encontro un empleado con ese legajo, o ese empleado ya posee una cuenta</p>');
                        $('#formularioRegistro').css('display', 'none');
                    }
            });  
        }
    });
}

function aceptarBusquedaEmpleado(){
    $('#resultadoBusquedaEmpleado').on('click', '#aceptarBusquedaEmpleado', function(){
        cargarRoles();
        console.log('############## aceptarBusquedaEmpleado()');
        $('#formularioRegistro').css('display', 'block');
    });
}

function registrarUsuario(empleado){
    console.log('############## registrarUsuario()');
    
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
        
/*------------------------------- verifico el campo de usuario */
        $('#usuario').on('input', function(){
            // verificar el usuario
            var usuario = $('#usuario').val();
            if(erUsuario.test(usuario) && usuario.length >= 8){
/*----------------------------------------------------------------------------*/
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
                            usuarioValido = usuarioValido + usuario;
                        }
                    },
                    error: function() {
                        $('#mensajeUsuario').text('Error al verificar el usuario.');
                        verificarUsuario = false;
                    }
                });
/*----------------------------------------------------------------------------*/
            }
            else{
                $('#mensajeUsuario').text('Usuario incorrecto, debe tener al menos 8 caracteres, sin espacios');
                $('#mensajeUsuario').css('color', 'red');
                verificarUsuario = false;
            }
            console.log(verificarUsuario);
        });
        
/*---------------------------------- verifico el campo de clave */
        $('#clave').on('input', function(){
            var clave = $('#clave').val();
            if(erPassword.test(clave) && clave.length >= 8){
                console.log('################clave valido');
                $('#mensajeClave').text('Clave valida');
                $('#mensajeClave').css('color', 'green');
                verificarClave = true;
            }
            else{
                console.log('###############clave incorrecto');
                $('#mensajeClave').text('Clave incorrecta, debe tener al menos 8 caracteres, sin espacios');
                $('#mensajeClave').css('color', 'red');
                verificarClave = false;
            }
        });

/*---------------------------------- verifico el campo de repetir clave */
        $('#repetirClave').on('input', function(){
            // verificar repetir clave
            var clave = $('#clave').val();
            var repetirClave = $('#repetirClave').val();
            if(erPassword.test(clave) && clave.length >= 8 && clave === repetirClave){
                console.log('################ ambas claves son validas');
                $('#mensajeRepetirClave').text('Clave valida');
                $('#mensajeRepetirClave').css('color', 'green');
                verificarRepetirClave = true;
                claveValida = claveValida + repetirClave;
            }
            else if(erPassword.test(clave) && clave.length >= 8 && clave !== repetirClave){
                console.log('################ ambas claves son validas');
                $('#mensajeRepetirClave').text('Las claves ingresadas no coinciden');
                $('#mensajeRepetirClave').css('color', 'red');
                verificarRepetirClave = false;
            }
            else{
                console.log('############### ');
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
        }
        else{
            $('#mensajeCompletar').text('Complete los campos de usuario y clave para poder registrarsr');
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
        data: JSON.stringify(usuario), // convierto el objeto a json
        succes: function(response){
            console.log('respuesta del servidor:', response);
        },
        error: function(xhr, status, error){
            console.error("Error al enviar datos:", error);
        }
    });
}
//recibo la respuesta listaRoles e inserto en la pestania desplegable