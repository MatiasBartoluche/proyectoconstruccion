$(document).ready(function(){
    var legajo = localStorage.getItem('detalleEmpleado');
    
    detalleEmpleado(legajo);
    
    habilitarCamposEmpleado();
    
    var imagenActual = null;
    
    cargarImagen(imagenActual);
    
    toggleListas('#btnHistorialTrabajos', '#historialTrabajos', 'historial de trabajo');
    toggleListas('#btnHistorialART', '#historialART', 'historial de ART');
    toggleListas('#btnHistorialAsistencias', '#historialAsistencias', 'historial de asistencias');
    toggleListas('#btnHistorialSueldos', '#historialSueldos', 'liquidacion de sueldos');
    toggleListas('#btnHistorialEPP', '#historialEPP', 'entregas de EPP');
    
    cerrarDetalleModal();
    
    cancelar();
});

function detalleEmpleado(legajo){
    $.ajax({
        url: '/proyectoconstruccion/SvDetalleEmpleado', // URL del servlet
        type: 'GET',
        data: {detalleLegajo: legajo},
        dataType: 'json',
        success: function (response) {
            insertarDetalleEmpleado(response);
            console.log(response);
            
            if(response.foto_dni_base64){
                imagenActual = response.foto_dni_base64;
            }
        },
        error: function (xhr, status, error) {
            console.error('Error al obtener empleados:', error);
        }
    });
}

function insertarDetalleEmpleado(empleado){
    $('#detalleNombresEmpleado').val(empleado.nombres);
    $('#detalleApellidosEmpleado').val(empleado.apellidos);
    
    var detalleCuil = empleado.cuil.split('-');
    $('#detalleDigitoGlobal').val(detalleCuil[0]);
    $('#detalleCuerpoCuil').val(detalleCuil[1]);
    $('#detalleDigitoVerificador').val(detalleCuil[2]);
    
    
    // cargando campos que posiblemente sean null
    if (empleado.foto_dni_base64) {
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

function habilitarCamposEmpleado(){
    $('#modificarDatosEmpleado').click(function(){
        //habilito los campos
        $('#detalleNombresEmpleado').prop('disabled', false);
        $('#detalleApellidosEmpleado').prop('disabled', false);

        $('#detalleDigitoGlobal').prop('disabled', false);
        $('#detalleCuerpoCuil').prop('disabled', false);
        $('#detalleDigitoVerificador').prop('disabled', false);

        // mostrar carga de foto dni
        $('#nuevaFotoDni').css('display', 'block');

        $('#detalleCalleEmpleado').prop('disabled', false);
        $('#detalleNumeroEmpleado').prop('disabled', false);
        $('#detalleDptoEmpleado').prop('disabled', false);
        $('#detalleLocalidadEmpleado').prop('disabled', false);

        $('#detalleTelefonoEmpleado').prop('disabled', false);
        $('#detalleTelefonoFamiliarEmpleado').prop('disabled', false);

        $('#detalleLegajoEmpleado').prop('disabled', false);
        $('#detalleSueldoEmpleado').prop('disabled', false);
        $('#detalleFechaEmpleado').prop('disabled', false);

        // primero habilito los campos <select>
        $('#detalleContratoEmpleado').prop('disabled', false);
        $('#detalleJerarquiaEmpleado').prop('disabled', false);     
        
        // luego cargo con todos los datos de la base de datos
        cargarDatos($('#detalleContratoEmpleado').val(), $('#detalleJerarquiaEmpleado').val());
        
        $('#guardarNuevosDatosEmpleado').css('display', 'block');
        $('#cancelarNuevosDatos').css('display', 'block');
        
        $('#modificarDatosEmpleado').css('display', 'none');
    });
}

function cargarDatos(idContratoActual, idJerarquiaActual){
    // vacio los select para evitar <option> duplicados
    $('#detalleContratoEmpleado').empty();
    $('#detalleJerarquiaEmpleado').empty();
    
    $.ajax({
        url: '/proyectoconstruccion/SvNuevoEmpleado', // URL del servlet
        type: 'GET',
        dataType: 'json',
        success: function (response) {
            // 'data' es un array de objetos Usuario en formato JSON
            // Aquí puedes iterar y mostrar los datos en tu página
            $.each(response.jerarquias, function (i, item) {
                if(item.id_jerarquia === idJerarquiaActual){
                    // agrego selected al <option> a insertar
                    $('#detalleJerarquiaEmpleado').append('<option value="' + item.id_jerarquia + '" id="' + item.descripcion + ' selected">' + item.descripcion + '</option>');
                }
                else{
                    $('#detalleJerarquiaEmpleado').append('<option value="' + item.id_jerarquia + '" id="' + item.descripcion + '">' + item.descripcion + '</option>');
                }
            });
            
            $.each(response.contratos, function (i, item) {
                if(item.id_contrato === idContratoActual){
                    $('#detalleContratoEmpleado').append('<option value="' + item.id_contrato + '" id="' + item.descripcion + '" selected>' + item.descripcion + '</option>');
                }
                else{
                    $('#detalleContratoEmpleado').append('<option value="' + item.id_contrato + '" id="' + item.descripcion + '">' + item.descripcion + '</option>');
                }
            });
        },
        error: function (xhr, status, error) {
            console.error('Error al obtener la lista de roles:', error);
        }
    });
}

function capturarNuevosDatos(imagen){

    var nuevoNombre = $('#detalleNombresEmpleado').val();
    var nuevoApellido = $('#detalleApellidosEmpleado').val();

    var nuevoDigitoGlobal = $('#detalleDigitoGlobal').val();
    var nuevoCuerpoCuil = $('#detalleCuerpoCuil').val();
    var nuevoDigitoVerificador = $('#detalleDigitoVerificador').val();

    var nuevaCalle = $('#detalleCalleEmpleado').val();
    var nuevoNumero = $('#detalleNumeroEmpleado').val();
    var nuevoDpto = $('#detalleDptoEmpleado').val();
    var nuevaLocalidad = $('#detalleLocalidadEmpleado').val();
    var nuevoTelefono = $('#detalleTelefonoEmpleado').val();
    var nuevoTelefonoFamiliar = $('#detalleTelefonoFamiliarEmpleado').val();

    var nuevoLegajo = $('#detalleLegajoEmpleado').val();
    var nuevoSueldo = $('#detalleSueldoEmpleado').val();
    var nuevaFecha = $('#detalleFechaEmpleado').val();

    var idContrato = $('#detalleContratoEmpleado').val();
    var descripcionContrato = $('#detalleContratoEmpleado option:selected').html();

    var idJerarquia = $('#detalleJerarquiaEmpleado').val();
    var descripcionJerarquia = $('#detalleJerarquiaEmpleado option:selected').html();

// ------------------------------- validacion de campos -----------------------------------------

    // variables ligadas a los campo que no pueden ser nulos
    //cuando se cumpla las condiciones de campos correctos, se camba a true
    var verificarNombres = false;
    var verificarApellidos = false;
    var verificarCuil = false;
    var verificarLegajo = false;
    var verificarSalario = false;
    var verificarFecha = false;


    if (nuevoNombre !== "") {
        verificarNombres = true;
    } else {
        $('#contenedorTextoModal').append('<p>El campo de nombre no puede ser vacio</p>');
        verificarNombres = false;
    }

    if (nuevoApellido !== "") {
        verificarApellidos = true;
    } else {
        $('#contenedorTextoModal').append('<p>El campo de apellido no puede estar vacio</p>');
        verificarApellidos = false;
    }

    if (nuevoDigitoGlobal === '' && nuevoCuerpoCuil === '' && nuevoDigitoVerificador === '') {
        $('#contenedorTextoModal').append('<p>Los campos del CUIL no pueden estar vacios</p>');
        verificarCuil = false;
    } else {
        $('#contenedorCuil div').empty();
        if (nuevoDigitoGlobal.length === 2 && nuevoCuerpoCuil.length === 8 && nuevoDigitoVerificador.length === 1) {
            verificarCuil = true;
        } else {
            if (nuevoDigitoGlobal.length !== 2) {
                $('#contenedorTextoModal').append('<p>El digito verificador debe ser de 2 digitos</p>');
                verificarCuil = false;
            }
            if (nuevoCuerpoCuil.length !== 8) {
                $('#contenedorTextoModal').append('<p>El cuerpo del cuil debe ser de 8 digitos</p>');
                verificarCuil = false;
            }
            if (nuevoDigitoVerificador.length !== 1) {
                $('#contenedorTextoModal').append('<p>El digito verificador debe ser de 1 digito</p>');
                verificarCuil = false;
            }
        }
    }

    if (nuevoLegajo !== "") {
        verificarLegajo = true;
    } else {
        $('#contenedorTextoModal').append('<p>El campo legajo no puede estar vacio</p>');
        verificarLegajos = false;
    }

    if (nuevoSueldo !== "" && descripcionContrato !== 'Subcontratista') {
        verificarSalario = true;
        console.log('---------- armar objeto Empleado con salario');
    } else if (nuevoSueldo === "" && descripcionContrato !== 'Subcontratista') {
        $('#contenedorTextoModal').append('<p>El campo sueldo no puede estar vacio</p>');
        verificarSalarios = false;
    } else if (descripcionContrato === 'Subcontratista') {
        salarioEmpleado = null;
        verificarSalario = true;
        console.log('---------- armar objeto Empleado sin salario');
    }

    if (nuevaFecha !== "") {
        verificarFecha = true;
    } else {
        $('#contenedorTextoModal').append('<p>El campo fecha de ingreso no puede estar vacio</p>');
        verificarFecha = false;
    }
// ----------------------------------------------------------------------------------------------

    // si todos los campos a verificar son correctos, armo el objeto empleado y lo envio por ajax
    if (verificarNombres === true && verificarApellidos === true && verificarCuil === true
            && verificarLegajo === true && verificarSalario === true && verificarFecha === true) {
        console.log('---------------- armar json');

        var jerarquia = {id_jerarquia: idJerarquia, descripcion: descripcionJerarquia};
        var contrato = {id_contrato: idContrato, descripcion: descripcionContrato};

        var empleadoModificado = {};

        $.extend(empleadoModificado, {legajo: nuevoLegajo});
        $.extend(empleadoModificado, {jerarquia: jerarquia});
        $.extend(empleadoModificado, {nombres: nuevoNombre});
        $.extend(empleadoModificado, {apellidos: nuevoApellido});
        $.extend(empleadoModificado, {cuil: nuevoDigitoGlobal + '-' + nuevoCuerpoCuil + '-' + nuevoDigitoVerificador});
        $.extend(empleadoModificado, {foto_dni_base64: imagen});
        $.extend(empleadoModificado, {fecha_ingreso: nuevaFecha});
        $.extend(empleadoModificado, {contrato: contrato});
        $.extend(empleadoModificado, {sueldo_base: nuevoSueldo});

        // ajustarDato() recibe el dato capturado y en caso de ser '' retorna null
        // para evitar errores en el servlet al crear una insyancia de Empleado con campos incorrectos
        $.extend(empleadoModificado, {calle: ajustarDato(nuevaCalle)});
        $.extend(empleadoModificado, {altura: ajustarDato(nuevoNumero)});
        $.extend(empleadoModificado, {piso: ajustarDato(nuevoDpto)});
        $.extend(empleadoModificado, {localidad: ajustarDato(nuevaLocalidad)});
        $.extend(empleadoModificado, {telefono: ajustarDato(nuevoTelefono)});
        $.extend(empleadoModificado, {telefono_familiar: ajustarDato(nuevoTelefonoFamiliar)});

        console.log('---------------- empleado modificado');
        console.log(empleadoModificado);
        
        guardarEmpleadoModificado(empleadoModificado);
    } else {
        $('#mensajeModalModificarEmpleado').css('display', 'block');
    }
}

function ajustarDato(variable) {
    var campo = variable;
    var retornar = null;

    if (campo !== '') {
        retornar = campo;
    }

    if (campo === 'Sin registrar') {
        retornar = null;
    }

    return retornar;
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

function cargarImagen(imagenActual){
    $('#guardarNuevosDatosEmpleado').click(function(){
        const FileInput = document.getElementById("nuevaFotoDni");
        const File = FileInput.files[0];

        // si detecta que se cargo una nueva foto
        if (File) {
            // Convertir la imagen a base64
            const reader = new FileReader();
            reader.onload = function (event) {
                const base64Image = event.target.result.split(",")[1]; // Remover el encabezado "data:image/png;base64,"
                // paso la imagen en base64
                capturarNuevosDatos(base64Image);
            };
            reader.readAsDataURL(File);
            console.log("existe imagen nueva");
        }
        // en caso de no haber nueva foto
        else{
            console.log("no existe imagen nueva, convirtiendo la imagen anterior");
 
             // Obtener el elemento de la imagen
            const img = document.getElementById('detalleFotoDni');

            // Crear un canvas
            const canvas = document.createElement('canvas');
            const ctx = canvas.getContext('2d');

            // Ajustar el tamaño del canvas al tamaño de la imagen
            canvas.width = img.width;
            canvas.height = img.height;

            // Dibujar la imagen en el canvas
            ctx.drawImage(img, 0, 0);

            // Convertir el contenido del canvas a una cadena Base64
            const base64String = canvas.toDataURL('image/png'); // Cambia 'image/png' a 'image/jpeg' si corresponde
            const base64Data = base64String.replace(/^data:image\/\w+;base64,/, '');
            capturarNuevosDatos(base64Data);
        }
    });
}

function cerrarDetalleModal(){
    $('#cerrarDetalleModal').click(function(){
        $('#contenedorTextoModal').empty();
        $('#mensajeModalModificarEmpleado').css('display', 'none');
    });
}

function guardarEmpleadoModificado(empleado){
    
    $.ajax({
        url: '/proyectoconstruccion/SvEmpleados', // URL del servlet
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(empleado),
        dataType: 'json',
        success: function (response){
            console.log(response);
            mensajeModal(response);
        },
        error: function (xhr, status, error) {
            console.error('Error al guardar el empleado:', error);
        }
    });
}

function cancelar(){
    $('#cancelarNuevosDatos').click(function(){
        //deshabilito los campos
        $('#detalleNombresEmpleado').prop('disabled', true);
        $('#detalleApellidosEmpleado').prop('disabled', true);

        $('#detalleDigitoGlobal').prop('disabled', true);
        $('#detalleCuerpoCuil').prop('disabled', true);
        $('#detalleDigitoVerificador').prop('disabled', true);

        // mostrar carga de foto dni
        $('#nuevaFotoDni').css('display', 'block');

        $('#detalleCalleEmpleado').prop('disabled', true);
        $('#detalleNumeroEmpleado').prop('disabled', true);
        $('#detalleDptoEmpleado').prop('disabled', true);
        $('#detalleLocalidadEmpleado').prop('disabled', true);

        $('#detalleTelefonoEmpleado').prop('disabled', true);
        $('#detalleTelefonoFamiliarEmpleado').prop('disabled', true);

        $('#detalleLegajoEmpleado').prop('disabled', true);
        $('#detalleSueldoEmpleado').prop('disabled', true);
        $('#detalleFechaEmpleado').prop('disabled', true);

        // primero habilito los campos <select>
        $('#detalleContratoEmpleado').prop('disabled', true);
        $('#detalleJerarquiaEmpleado').prop('disabled', true); 
        
        $('#guardarNuevosDatosEmpleado').css('display', 'none');
        $('#cancelarNuevosDatos').css('display', 'none');
        
        $('#modificarDatosEmpleado').css('display', 'block');
    });
}