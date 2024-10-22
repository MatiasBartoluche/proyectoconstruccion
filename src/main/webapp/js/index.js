$(document).ready(function(){
    
    login();
    aceptar();
});

function verificarLongitud(){
    // validar usuario
    const erUsuario = /^[a-z0-9_-]{3,16}$/;
    //Validar password
    const erPassword = /^[a-z0-9_-]{6,18}$/;

    var usuario = $('#usuario').val();
    var clave = $('#pass').val();

    var longitudUsuario = 0 + usuario.length;
    var longitudClave = 0 + clave.length;

    var verificar = false;
    console.log(longitudUsuario + ' - ' + longitudClave);

    if (longitudUsuario < 8) {
        $('#mensajeIncorrecto').text('El usuario y clave deben tener al menos 8 caracteres');
        $('#mensajeIncorrecto').css('color', 'red');
        verificar = false;
    } else if (longitudUsuario >= 8 && erUsuario.test(usuario)) {
        $('#mensajeIncorrecto').text('');
        verificar = true;
    }

    if (longitudClave < 8) {
        $('#mensajeIncorrecto').text('El usuario y clave deben tener al menos 8 caracteres');
        $('#mensajeIncorrecto').css('color', 'red');
        verificar = false;
    } else if (longitudClave >= 8 && erPassword.test(clave)) {
        $('#mensajeIncorrecto').text('');
        verificar = true;
    }
    return verificar;
}

function login(){
    $('#ingresar').click(function () {
        var usuario = $('#usuario').val();
        var clave = $('#pass').val();
        
        console.log(usuario+' - '+clave);
        
        var verificar = verificarLongitud();
        
        var claveEncriptada = CryptoJS.SHA256(clave).toString();
        
        if(verificar === true){
            console.log('enviar datos al servidor');
            
            $.ajax({
                url: 'SvIndex',
                type: 'POST',
                //contentType: 'application/json; charset=utf-8', // especifico que es json
                data: {usuario: usuario, clave: claveEncriptada},
                success: function (response) {
                    console.log(response);
                    if (response.mensaje === false) {
                        console.log('Usuario y/o clave incorrectos');
                        $('#mensajeIncorrecto').text('Usuario y/o claves incorrectas');
                        $('#mensajeIncorrecto').css('color', 'red');
                    }
                    else if(response.mensaje === true && response.autorizado === true){
                        window.location.href = "/proyectoconstruccion/vistas/home.jsp";
                        console.log('Bienvenido');
                    }
                     else if(response.mensaje === true && response.autorizado === false){
                         window.location.href = "/proyectoconstruccion/vistas/noaprobado.jsp";
                         console.log('Usuario no autorizado');
                     }
                },
                error: function (xhr, status, error) {
                    console.error("Error:", error);
                }
            });
            
        }
        else{
            console.log('usuario y/o contrasenia incorrectos');
        }
    });
}

function aceptar(){
    $('#btnNoAprobado').click(function(){
        console.log('boton no aprobado');
        window.location.href = "../index.jsp";
    });
}