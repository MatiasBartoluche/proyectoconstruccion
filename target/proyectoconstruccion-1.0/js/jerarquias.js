$(document).ready(function(){
    
    capturarJerarquia();
    modalJerarquia();
    
    cargarJerarquias();
    
    guardarJerarquia();
    modificarJerarquia();
    borrarJerarquia();
    
    modalCancelarJerarquia();
});

function capturarJerarquia(){
    $('#guardarCargo').click(function(){
        var desc = $('#nuevoCargo').val();
        if(desc !== ''){
            //$('#mensajeModalJerarquia').css('display', 'block');
            //$('#mensajeModalTexto').text('El nuevo carho ha sido guardado');
            $('#modalCancelarJerarquia').hide();
            $('#modalBorrarJerarquia').hide();
            $('#contenedorCargos').empty();
            nuevaJerarquia(desc);
            cargarJerarquias();
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
        $('#contenedorCargos').empty();
        cargarJerarquias(); // vuelvo a cargar los cargos
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

function cargarJerarquias(){
    console.log('Cargando jerarquias');
    $.ajax({
        url: '/proyectoconstruccion/SvJerarquias', // URL del servlet
        type: 'GET',
        data: {mensaje: 'cargar'},
        dataType: 'json',
        success: function (response){
            for (indice = 0; indice < response.length; indice++) {
                insertarJerarquia(response[indice], indice);
            }               
        },
        error: function (xhr, status, error) {
            console.error('Error al guardar el empleado:', error);
        }
    });
}

function insertarJerarquia(jerarquia, contador){
    var clase = '';
    
    if(contador%2 === 0){
        clase = 'impar';
    }
    else{
        clase = 'par';
    }

    $('#contenedorCargos').append('<div class="cargo '+clase+'">' +
                                '<input type="text" class="input-editar '+clase+'" id="input'+jerarquia.id_jerarquia+'" disabled="true" value="'+jerarquia.descripcion+'">'+
                                '<div class="botonesJerarquia">' +
                                    '<span class="btnIcono btnGuardarJerarquia" id="guardar-' + jerarquia.id_jerarquia + '" data-idGuardar="' + jerarquia.id_jerarquia + '">' +
                                        '<i class="bx bx-check icon"></i>' +
                                    '</span>' +
                                    '<span class="btnIcono btnModificarJerarquia" id="editar-' + jerarquia.id_jerarquia + '" data-idEditar="' + jerarquia.id_jerarquia + '">' +
                                        '<i class="bx bx-edit-alt icon"></i>' +
                                    '</span>' +
                                    '<span class="btnIcono btnBorrarJerarquia" id="borrar-' + jerarquia.id_jerarquia + '" data-idBorrar="' + jerarquia.id_jerarquia + '">' +
                                        '<i class="bx bx-trash icon"></i>' +
                                    '</span>' +
                                '</div>' +
                            '</div>');
}

function guardarJerarquia(){
    $('#listaCargos').on('click', '.btnGuardarJerarquia', function () {
        var id = $(this).attr('id').split('-')[1];
        $('#guardar-'+id).hide();
        $('#editar-'+id).show();
        $('#input'+id).prop('disabled', false);
        
        var capturarDescripcion = $('#input'+id).val();
        console.log(id+' - '+capturarDescripcion);
        
        var objeto = {mensaje: 'editar', idJerarquia: id, descripcion: capturarDescripcion};
        solicitudJerarquia(objeto);
        
        $('#input'+id).prop('disabled', true);
        
        $('#modalBorrarJerarquia').hide();
        $('#modalCancelarJerarquia').hide();
    });
}

function modificarJerarquia(){
    $(document).on('click', '.btnModificarJerarquia', function () {
        var id = $(this).attr('id').split('-')[1];
        console.log('editar: '+id);
        
        $('#guardar-'+id).css('display', 'flex');
        $('#editar-'+id).hide();
        
        $('#input'+id).prop('disabled', false);
    });
}

function borrarJerarquia(){
    $('#listaCargos').on('click', '.btnBorrarJerarquia', function () {
        var id = $(this).attr('id').split('-')[1];
        console.log('borrar: '+id);

        var capturarDescripcion = $('#input'+id).val();
        console.log('eliminando: '+id);
        
        var objeto = {mensaje: 'borrar', idJerarquia: id};
        //solicitudJerarquia(objeto);
        mensajeModal('¿Esta seguro de que desaea eliminar este cargo? '+capturarDescripcion);
        $('#modalJerarquia').hide();
        $('#modalBorrarJerarquia').show();
        $('#modalCancelarJerarquia').show();
        
        modalBorrarJerarquia(objeto);
    });
}

function modalCancelarJerarquia(){
    $('#modalCancelarJerarquia').click(function(){
        //$('#mensajeModalTexto').text();
        $('#mensajeModalJerarquia').css('display', 'none');
        console.log('cancelar borrado');
    });
}

function modalBorrarJerarquia(objeto){
    $('#modalBorrarJerarquia').click(function(){
        $('#mensajeModalJerarquia').css('display', 'none');
        $('#modalCancelarJerarquia').hide();
        $('#modalBorrarJerarquia').hide();
        $('#modalJerarquia').show();
        
        console.log('aceptar borrado');
        solicitudJerarquia(objeto);
    });
}

function solicitudJerarquia(objeto){
    $.ajax({
        url: '/proyectoconstruccion/SvJerarquias', // URL del servlet
        type: 'GET',
        data: objeto,
        dataType: 'json',
        success: function (response) {
            console.log(response);
            mensajeModal(response.mensaje);
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}