$(document).ready(function(){
    aceptarGrupo();
    cancelarGrupo();
    
    $('#cancelarGrupo').prop('disabled', true);
    
    crearGrupo();
    
    cerrarModalGrupos();
});

function aceptarGrupo(){
    $('#aceptarGrupo').click(function(){
        
        $('#cancelarGrupo').prop('disabled', false);
        
        var idGrupo = $('#seleccionarGrupo option:selected').attr('id');
        
        $('#aceptarGrupo').prop('disabled', true);
        $('#seleccionarGrupo').prop('disabled', true);
        
        if(idGrupo === '0'){
            toggleListas('#capataces', true);
            toggleListas('#empleados', true);
            solicitudAjax('Capataz');
        }
        else if(idGrupo === '1'){
            toggleListas('#subcontratistas', true);
            toggleListas('#empleados', true);
            solicitudAjax('Subcontratista');
        }
    });
}

function cancelarGrupo(){
    $('#cancelarGrupo').click(function(){
        $('#cancelarGrupo').prop('disabled', true);
        $('#aceptarGrupo').prop('disabled', false);
        $('#seleccionarGrupo').prop('disabled', false);
        
        // ocultar listas al cancelar
        toggleListas('#capataces', false);
        toggleListas('#subcontratistas', false);
        toggleListas('#empleados', false);
        
        // vaciar listas al cancelar
        $('#contenedorCapataces').empty();
        $('#contenedorSubcontratistas').empty();
        $('#contenedorEmpleados').empty();
    });
}

function toggleListas(idLista, booleano){
    $(idLista).toggle(300, function(){
        if(booleano === true){
            $(idLista).css('display', 'flex');  
        }
        else{
            $(idLista).css('display', 'none');  
        }
    });
}

function solicitudAjax(mensaje){
    $.ajax({
        url: '/proyectoconstruccion/SvGrupoTrabajo', // URL del servlet
        type: 'GET',
        data: {mensaje: mensaje},
        dataType: 'json',
        success: function (response) {
            console.log(response);
            if(response.mensaje){
                console.log(mensaje);
                console.log(response.mensaje);
            }
            else{
                if (response.capataces) {
                    insertarCapataces(response.capataces, '#contenedorCapataces');
                }

                if (response.empleados) {
                    insertarEmpleados(response.empleados, '#contenedorEmpleados');
                }

                if (response.subcontratistas) {
                    insertarSubcontratistas(response.subcontratistas, '#contenedorSubcontratistas');
                }
            }
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function insertarCapataces(lista, idContenedor){
    var clase = 'impar';
    var contador = 0;
    
    lista.forEach( item => {
        console.log(item);
        if(contador%2 === 0){
            clase = 'impar';
            contador = contador + 1;
        }
        else{
            clase = 'par';
            contador = contador + 1;
        }
        $(idContenedor).append('<div class="empleado '+clase+'">'+
                                    '<p>'+item.legajo+'</p>'+
                                    '<p>'+item.apellidos+', '+item.nombres+'</p>'+
                                    '<p>'+item.cuil+'</p>'+
                                    '<p>'+item.jerarquia.descripcion+'</p>'+
                                    '<p>'+item.fecha_ingreso+'</p>'+
                                    '<div class="botonesEmpleado" id"check-'+item.id_empleado+'">'+
                                       '<input type="radio" name="capataz" id="'+item.id_empleado+'" value="'+item.id_empleado+'">'+
                                    '</div>'+
                                '</div>');
    });
}

function insertarEmpleados(lista, idContenedor){
    var clase = 'impar';
    var contador = 0;
    
    lista.forEach( item => {
        console.log(item);
        if(contador%2 === 0){
            clase = 'impar';
            contador = contador + 1;
        }
        else{
            clase = 'par';
            contador = contador + 1;
        }
        $(idContenedor).append('<div class="empleado '+clase+'">'+
                                    '<p>'+item.legajo+'</p>'+
                                    '<p>'+item.apellidos+', '+item.nombres+'</p>'+
                                    '<p>'+item.cuil+'</p>'+
                                    '<p>'+item.jerarquia.descripcion+'</p>'+
                                    '<p>'+item.fecha_ingreso+'</p>'+
                                    '<div class="botonesEmpleado" id"check-'+item.id_empleado+'">'+
                                       '<input type="checkbox" id="'+item.id_empleado+'" value="'+item.id_empleado+'">'+
                                    '</div>'+
                                '</div>');
    });
}

function crearGrupo(){
    $('#crearGrupo').on('click', function(){
        var nombreGrupo = $('#nombreGrupo').val();
        var idCapataz = $('input[name="capataz"]:checked').val();
        var grupoTrabajo = {};
        
        var empleados = [];
        $('input[type="checkbox"]:checked').each(function() {
          empleados.push(empleado = {id_empleado: $(this).val()});
        });
        console.log('Crear grupo');
        console.log('nombre: '+nombreGrupo);
        console.log('capataz: '+idCapataz);
        console.log('empleados: '+empleados);
        
        if(idCapataz === undefined){
            modalGrupos('No se puede crear un grupo sin jefe de grupo', '#aceptarModalGrupos');
        }
        else{
            $.extend(grupoTrabajo, {nombre_grupo: nombreGrupo});
            $.extend(grupoTrabajo, {capataz: empleado = {id_empleado: idCapataz}});
            $.extend(grupoTrabajo, {empleados: empleados});
            console.log(grupoTrabajo);
            
            nuevoGrupo(grupoTrabajo);
        }
    });
}

function nuevoGrupo(grupo){
    $.ajax({
        url: '/proyectoconstruccion/SvGrupoTrabajo', // URL del servlet
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(grupo),
        dataType: 'json',
        success: function (response) {
            modalGrupos(response.mensaje, '#cerrarModalGRupos');

        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}

function modalGrupos(respuesta, idBoton){
    console.log(respuesta);
    $('#contenedorTextoModal').empty();
    $('#mensajeModalGrupos').css('display', 'block');
    $('#contenedorTextoModal').append('<p>'+respuesta+'</p>');
    if(idBoton === '#cerrarModalGrupo'){
        $('#aceptarModalGrupos').hide();
        cerrarModalGrupos();
    }

}

function cerrarModalGrupos(){
    $('#cerrarModalGrupo').click(function(){
        $('#contenedorTextoModal').empty();
        $('#mensajeModalGrupos').css('display', 'none');
        
        // vaciar listas al cancelar
        $('#contenedorCapataces').empty();
        $('#contenedorSubcontratistas').empty();
        $('#contenedorEmpleados').empty();
        
        toggleListas('#capataces', false);
        toggleListas('#empleados', false);
        toggleListas('#subcontratistas', false);
        
        $('#cancelarGrupo').prop('disabled', true);
        $('#aceptarGrupo').prop('disabled', false);
    });
}