$(document).ready(function(){
    var legajo = localStorage.getItem('detalleEmpleado');
    
    detalleEmpleado(legajo);
    
    toggleListas('#btnHistorialTrabajos', '#historialTrabajos', 'historial de trabajo');
    toggleListas('#btnHistorialART', '#historialART', 'historial de ART');
    toggleListas('#btnHistorialAsistencias', '#historialAsistencias', 'historial de asistencias');
    toggleListas('#btnHistorialSueldos', '#historialSueldos', 'liquidacion de sueldos');
    toggleListas('#btnHistorialEPP', '#historialEPP', 'entregas de EPP');
});

function detalleEmpleado(legajo){
    console.log(legajo);
    
    $.ajax({
        url: '/proyectoconstruccion/SvDetalleEmpleado', // URL del servlet
        type: 'GET',
        data: {detalleLegajo: legajo},
        dataType: 'json',
        success: function (response) {
            insertarDetalleEmpleado(response);
        },
        error: function (xhr, status, error) {
            console.error('Error al obtener empleados:', error);
        }
    });
}

function insertarDetalleEmpleado(empleado){
    console.log(empleado);
    
    $('#detalleNombresEmpleado').val(empleado.nombres);
    $('#detalleApellidosEmpleado').val(empleado.apellidos);
    
    var detalleCuil = empleado.cuil.split('-');
    $('#detalleDigitoGlobal').val(detalleCuil[0]);
    $('#detalleCuerpoCuil').val(detalleCuil[1]);
    $('#detalleDigitoVerificador').val(detalleCuil[2]);
    
    
    // cargando campos que posiblemente sean null
    if (empleado.foto_dni_base64) {
        console.log("existe foto");
        $('#detalleFotoDni').attr('src', `data:image/png;base64,${empleado.foto_dni_base64}`);
    } else {
        console.log("no hay foto");
        $('#detalleFotoDni').attr('alt', 'Foto no disponible');
    }
    
    if(empleado.calle){
        $('#detalleCalleEmpleado').val(empleado.calle);
    }
    else{
        $('#detalleCalleEmpleado').val('Sin registrar');
    }
    
    if(empleado.altura){
        $('#detalleNumeroEmpleado').val(empleado.altura);
    }
    else{
        $('#detalleNumeroEmpleado').val('Sin registrar');
    }
    
    if(empleado.piso){
        $('#detalleDptoEmpleado').val(empleado.piso);
    }
    else{
        $('#detalleDptoEmpleado').val('Sin registrar');
    }

    if(empleado.localidad){
        $('#detalleLocalidadEmpleado').val(empleado.localidad);
    }
    else{
        $('#detalleLocalidadEmpleado').val('Sin registrar');
    }
    
    if(empleado.telefono){
        $('#detalleTelefonoEmpleado').val(empleado.telefono);
    }
    else{
        $('#detalleTelefonoEmpleado').val('Sin registrar');
    }

    if(empleado.telefono_familiar){
        $('#detalleTelefonoFamiliarEmpleado').val(empleado.telefono_familiar);
    }
    else{
        $('#detalleTelefonoFamiliarEmpleado').val('Sin registrar');
    }
    
    $('#detalleLegajoEmpleado').val(empleado.legajo);
    $('#detalleSueldoEmpleado').val(empleado.sueldo_base);
    $('#detalleFechaEmpleado').val(empleado.fecha_ingreso);
    
    $('#detalleContratoEmpleado').append('<option value="' + empleado.contrato.id_contrato + '" id="' + empleado.contrato.descripcion + '">' + empleado.contrato.descripcion + '</option>');
    $('#detalleJerarquiaEmpleado').append('<option value="' + empleado.jerarquia.id_jerarquia+ '" id="' + empleado.jerarquia.descripcion + '">' + empleado.jerarquia.descripcion + '</option>');
    
    console.log(empleado.asignaciones);
    
    
    // cargando historiales de empleado
    if(empleado.asignaciones.length === 0){
        $('#historialTrabajos').append('<h1 class="mensajeHistorialVacio">No hay resultados de busqueda</h1>');
    }
    else{
        console.log('insertarAsignaciones');
    }
    
    if(empleado.asistencias.length === 0){
        $('#historialAsistencias').append('<h1 class="mensajeHistorialVacio">No hay resultados de busqueda</h1>');
    }
    else{
        console.log('insertarAsignaciones');
    }
    
    if(empleado.historialART.length === 0){
        $('#historialART').append('<h1 class="mensajeHistorialVacio">No hay resultados de busqueda</h1>');
    }
    else{
        console.log('insertarAsignaciones');
    }
    
    if(empleado.liquidaciones.length === 0){
        $('#historialSueldos').append('<h1 class="mensajeHistorialVacio">No hay resultados de busqueda</h1>');
    }
    else{
        console.log('insertarAsignaciones');
    }
    
    if(empleado.planillaEPP.length === 0){
        $('#historialEPP').append('<h1 class="mensajeHistorialVacio">No hay resultados de busqueda</h1>');
    }
    else{
        console.log('insertarAsignaciones');
    }
}

function toggleListas(idBoton, idLista, texto){
    $(idBoton).on('click', function () {
        const $contenido = $(idLista); // Seleccionar el div
        $contenido.toggle(300, function () {
            // Cambiar el texto del botón después de mostrar/ocultar
            var mostrar ='Ver '+texto;
            var ocultar ='Ocultar '+texto;
            
            const textoBoton = $contenido.is(':visible') ? ocultar : mostrar;
            $(idBoton).text(textoBoton);
        });
    });
}