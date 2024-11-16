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
                dataType: 'json',
                success: function (response) {
                    if(response.status === 'success'){
                        console.log('usuario correcto');
                        if(response.autorizado === true){
                            console.log(response);
                            console.log("ingresar al sistema");
                            window.location.href = response.redirectUrl;
                        }
                        else{
                            console.log("denegado");
                            console.log(response);
                            window.location.href = "/proyectoconstruccion/vistas/noaprobado.jsp";
                        }
                    }
                    else{
                        $('#mensajeIncorrecto').empty();
                        $('#mensajeIncorrecto').text('El usuario y/o clave son incorrectos');
                        $('#mensajeIncorrecto').css('color', 'red');
                        console.log('usuario y/o clave incorrectos');
                        console.log(response);
                    }
                },
                error: function (xhr, status, error) {
                    console.error("Error:", error);
                    console.error("Status:", status);
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