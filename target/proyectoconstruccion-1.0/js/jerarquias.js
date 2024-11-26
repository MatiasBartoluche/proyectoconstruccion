$(document).ready(function(){
    
    capturarJerarquia();
    modalJerarquia();
});

function capturarJerarquia(){
    $('#guardarCargo').click(function(){
        var desc = $('#nuevoCargo').val();
        if(desc !== ''){
            //$('#mensajeModalJerarquia').css('display', 'block');
            //$('#mensajeModalTexto').text('El nuevo carho ha sido guardado');
            nuevaJerarquia(desc);
        }
        else{
            $('#mensajeModalJerarquia').css('display', 'block');
            $('#mensajeModalTexto').text('El campo de texto no puede estar vacio');
        }
    });
}

function modalJerarquia(){
    $('#modalJerarquia').click(function(){
        $('#mensajeModalTexto').text();
        $('#mensajeModalJerarquia').css('display', 'none');
    });
}

function nuevaJerarquia(descripcion){
    $.ajax({
        url: '/proyectoconstruccion/SvJerarquias', // URL del servlet
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({descripcion: descripcion}),
        dataType: 'json',
        success: function (response){
            console.log(descripcion);
            console.log(response);
            mensajeModal(response.mensaje);
        },
        error: function (xhr, status, error) {
            console.error('Error al guardar el empleado:', error);
        }
    });
}

function mensajeModal(mensaje){
    $('#mensajeModalJerarquia').css('display', 'block');
    $('#mensajeModalTexto').text();
    $('#mensajeModalTexto').text(mensaje);
    $('#nuevoCargo').val('');
}