$(document).ready(function(){
    
    cargarDatos();
    
    cargarImagen();
    cerrarModal();
    aceptarModal();
    nuevoEmpleadoModal();
});

function cargarDatos(){
    $.ajax({
        url: '/proyectoconstruccion/SvNuevoEmpleado', // URL del servlet
        type: 'GET',
        dataType: 'json',
        success: function (response) {
            // 'data' es un array de objetos Usuario en formato JSON
            // Aquí puedes iterar y mostrar los datos en tu página
            $.each(response.jerarquias, function (i, item) {
                $('#jerarquia').append('<option value="' + item.id_jerarquia + '" id="' + item.descripcion + '">' + item.descripcion + '</option>');
            });
            
            $.each(response.contratos, function (i, item) {
                $('#contrato').append('<option value="' + item.id_contrato + '" id="' + item.descripcion + '">' + item.descripcion + '</option>');
            });
        },
        error: function (xhr, status, error) {
            console.error('Error al obtener la lista de roles:', error);
        }
    });
}

function cargarImagen(){
    $('#guardarNuevoEmpleado').click(function(){
        const fileInput = document.getElementById("dniEmpleado");
        const file = fileInput.files[0];

        var imagen = null;

        if (file) {
            // Convertir la imagen a base64
            const reader = new FileReader();
            reader.onload = function (event) {
                const base64Image = event.target.result.split(",")[1]; // Remover el encabezado "data:image/png;base64,"
                //console.log(base64Image);
                var imagen = {foto_dni: base64Image};
                // paso la imagen en base64
                capturarDatos(imagen);
            };
            reader.readAsDataURL(file);

            console.log("existe imagen");
        }
        else {
            console.log("no existe imagen");
                var imagen = {foto_dni: null};
                capturarDatos(imagen);
        }
    });
}

function capturarDatos(imagen){    
    //datos personales
    var nombres = $('#nombresEmpleado').val();
    var apellidos = $('#apellidosEmpleado').val();
    var digitoGlobal = $('#digitoGlobal').val();
    var cuerpoCUIL = $('#cuerpoCUIL').val();
    var digitoVerificador = $('#digitoVerificador').val();
    var dniEmpleado = imagen.foto_dni; // base64 de la imagen cargada o null
    
    // datos de contacto
    var calleEmpleado = $('#calleEmpleado').val();
    var numeroDomicilioEmpleado = $('#numeroDomicilioEmpleado').val();
    var pisoEmpleado = $('#pisoEmpleado').val();
    var localidadEmpleado = $('#localidadEmpleado').val();
    var telefonoEmpleado = $('#telefonoEmpleado').val();
    var telefonoFamiliarEmpleado = $('#telefonoFamiliarEmpleado').val();
    
    //datos administrativos
    var legajo = $('#legajo').val();
    
    var idJerarquia = $('#jerarquia').val();
    var descripcionJerarquia = $('#jerarquia option:selected').html();
    
    var idContrato = $('#contrato').val();
    var descripcionContrato = $('#contrato option:selected').html();
    var salarioEmpleado = $('#salarioEmpleado').val();
    var fechaIngreso = $('#fechaIngreso').val();
    
    // variables ligadas a los campo que no pueden ser nulos
    //cuando se cumpla las condiciones de campos correctos, se camba a true
    var verificarNombres = false;
    var verificarApellidos = false;
    var verificarCuil = false;
    var verificarLegajo = false;
    var verificarSalario = false;
    var verificarFecha = false;
    
    // objeto empleado vacio
    var empleado = {};
    
    console.log('-----------------------------------------------');
    console.log("calle :"+calleEmpleado);
    console.log("altura :"+numeroDomicilioEmpleado);
    console.log("oiso :"+pisoEmpleado);
    console.log("localidad :"+localidadEmpleado);
    console.log("telefono :"+telefonoEmpleado);
    console.log("telefono familiar :"+telefonoFamiliarEmpleado);
    console.log('-----------------------------------------------');
    

    
    if(nombres!==""){
        $('#contenedorNombres').css('border', '0');
        $('#contenedorNombres p').empty();
        verificarNombres = true;
    }
    else{
        $('#contenedorNombres').css('border', '1px solid red');
        $('#contenedorNombres p').empty();
        $('#contenedorNombres p').css('color', 'red');
        $('#contenedorNombres').css('border', '1px solid red');
        $('#contenedorNombres p').text("Este campo no puede estar vacio");
        verificarNombres = false;
    }
    
    if(apellidos!==""){
        $('#contenedorApellidos').css('border', '0');
        $('#contenedorApellidos p').empty();
        verificarApellidos = true;
    }
    else{
        $('#contenedorApellidos').css('border', '1px solid red');
        $('#contenedorApellidos p').empty();
        $('#contenedorApellidos p').css('color', 'red');
        $('#contenedorApellidos').css('border', '1px solid red');
        $('#contenedorApellidos p').text("Este campo no puede estar vacio");
        verificarApellidos = false;
    }
    
    if(digitoGlobal === '' && cuerpoCUIL === '' && digitoVerificador === ''){
        $('#contenedorCuil div').empty();
        $('#contenedorCuil').css('border', '1px solid red');
        $('#contenedorCuil div').append('<p>Los campos del CUIL no pueden estar vacios</p>');
        $('#contenedorCuil div p').css('color', 'red');
        verificarCuil = false;
    }
    else{
        $('#contenedorCuil div').empty();
        if(digitoGlobal.length === 2 && cuerpoCUIL.length === 8 && digitoVerificador.length === 1){
            $('#contenedorCuil').css('border', '0');
            $('#contenedorCuil div').empty();
            verificarCuil = true;
        }
        else{
            $('#contenedorCuil div').empty();
            if(digitoGlobal.length !== 2){
                $('#contenedorCuil div').append('<p>El digito verificador debe ser de 2 digitos</p>');
                verificarCuil = false;
            }
            if(cuerpoCUIL.length !== 8){
                $('#contenedorCuil div').append('<p>El cuerpo del cuil debe ser de 8 digitos</p>');
                verificarCuil = false;
            }
            if(digitoVerificador.length !== 1){
                $('#contenedorCuil div').append('<p>El digito verificador debe ser de 1 digito</p>');
                verificarCuil = false;
            }
            $('#contenedorCuil').css('border', '1px solid red');
            $('#contenedorCuil div p').css('color', 'red');
        }
    }
    
    if(legajo!==""){
        $('#contenedorLegajo').css('border', '0');
        $('#contenedorLegajo p').empty();
        verificarLegajo = true;
    }
    else{
        $('#contenedorLegajo').css('border', '1px solid red');
        $('#contenedorLegajo p').empty();
        $('#contenedorLegajo p').css('color', 'red');
        $('#contenedorLegajo').css('border', '1px solid red');
        $('#contenedorLegajo p').text("Este campo no puede estar vacio");
        verificarLegajos = false;
    }
    
    if(salarioEmpleado!==""){
        $('#contenedorSalario').css('border', '0');
        $('#contenedorSalario p').empty();
        verificarSalario = true;
    }
    else{
        $('#contenedorsalario').css('border', '1px solid red');
        $('#contenedorSalario p').empty();
        $('#contenedorSalario p').css('color', 'red');
        $('#contenedorSalario').css('border', '1px solid red');
        $('#contenedorSalario p').text("Este campo no puede estar vacio");
        verificarSalarios = false;
    }
    
    if(fechaIngreso!==""){
        $('#contenedorFechaIngreso').css('border', '0');
        $('#contenedorFechaIngreso p').empty();
        verificarFecha = true;
    }
    else{
        $('#contenedorFechaIngreso').css('border', '1px solid red');
        $('#contenedorFechaIngreso p').empty();
        $('#contenedorFechaIngreso p').css('color', 'red');
        $('#contenedorFechaIngreso').css('border', '1px solid red');
        $('#contenedorFechaIngreso p').text("Este campo no puede estar vacio");
        verificarFecha = false;
    }
    
    // si todos los campos a verificar son correctos, armo el objeto empleado y lo envio por ajax
    if (verificarNombres === true && verificarApellidos === true && verificarCuil === true
            && verificarLegajo === true && verificarSalario === true && verificarFecha === true){
        console.log('---------------- armar json');
        
        var jerarquia = {id_jerarquia: idJerarquia, descripcion: descripcionJerarquia};
        var contrato = {id_contrato: idContrato, descripcion: descripcionContrato};
        // agrego informacion al objeto empleado
        $.extend(empleado, { legajo: legajo});
        $.extend(empleado, { jerarquia: jerarquia});
        $.extend(empleado, { nombres: nombres });
        $.extend(empleado, { apellidos: apellidos });
        $.extend(empleado, { cuil: digitoGlobal+'-'+cuerpoCUIL+'-'+digitoVerificador});
        $.extend(empleado, { foto_dni_base64: dniEmpleado});
        $.extend(empleado, { fecha_ingreso: fechaIngreso});
        $.extend(empleado, { contrato: contrato});
        $.extend(empleado, { sueldo_base: salarioEmpleado});

    if(calleEmpleado !== ''){
        $.extend(empleado, { calle: calleEmpleado });
    }
    
    if(numeroDomicilioEmpleado !== ''){
        $.extend(empleado, { altura: numeroDomicilioEmpleado});
    }
    
    if(pisoEmpleado !== ''){
        $.extend(empleado, { piso: pisoEmpleado});
    }
    
    if(localidadEmpleado !== ''){
        $.extend(empleado, { localidad: localidadEmpleado});
    }

    if(telefonoEmpleado !== ''){
        $.extend(empleado, { telefono: telefonoEmpleado});
    }

    if(telefonoFamiliarEmpleado === ''){
        $.extend(empleado, { telefono_familiar: telefonoFamiliarEmpleado});
    }
        
        nuevoEmpleado(empleado);
    }
    else{
        console.log('---------------- campos invalidos');
    }
}

function nuevoEmpleado(empleado){
    console.log(empleado);
    
    $.ajax({
        url: '/proyectoconstruccion/SvNuevoEmpleado', // URL del servlet
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

function mensajeModal(respuesta){
    console.log('++++++++++++++++++ mensaje modal: '+respuesta.status);

    $('#mensajeModal').css('display', 'block');
    $('#mensajeModalTexto').text();
    $('#mensajeModalTexto').text(respuesta.mensaje);
    $('.botonesModal').empty();
    
    if(respuesta.status === true){
        $('#mensajeModalTexto').after('Si desea registrar un nuevo empleado, pulse el boton "Nuevo Empleado"');
         ('#mensajeModalTexto').after('Si desea finalizar, pulse el boton "Cerrar"');
        
        $('.botonesModal').append('<button id="btnModalNuevoEmpleado">Nuevo empleado</button>');
            $('.botonesModal').append('<button id="btnModalCerrar">Cerrar</button>');
    }
    else if(respuesta.status === false){
        $('#mensajeModalTexto').text('El numero de legajo ingresado ya esta asociado a otro empleado');
        $('.botonesModal').append('<button id="btnModalAceptar">Aceptar</button>');
    }
}

function cerrarModal(){
    $(document).on('click', '#btnModalCerrar', function(){
        console.log(' cerrar modal');
        $('#mensajeModal').css('display', 'none');
        window.location.href = "/proyectoconstruccion/vistas/sistemas/empleados.jsp";
    });
}

function aceptarModal(){
    $(document).on('click', "#btnModalAceptar",function(){
        console.log('aceptar modal');
        $('#mensajeModal').css('display', 'none');
    });
}

function nuevoEmpleadoModal(){
    $(document).on('click', '#btnModalNuevoEmpleado',function(){
        console.log('nuevo empleado modal');
        $('#mensajeModal').css('display', 'none');
        
        // vaciar todos los campos para ingresar nuevos datos
        
        $('#formularioNuevoEmpleado').trigger('reset');
        /*$('#nombresEmpleado').val();
        $('#apellidosEmpleado').val();
        $('#digitoGlobal').val();
        $('#cuerpoCUIL').val();
        $('#digitoVerificador').val();


        // datos de contacto
        $('#calleEmpleado').val();
        $('#numeroDomicilioEmpleado').val();
        $('#pisoEmpleado').val();
        $('#localidadEmpleado').val();
        $('#telefonoEmpleado').val();
        $('#telefonoFamiliarEmpleado').val();

        //datos administrativos
        $('#legajo').val();

        $('#jerarquia').val();
        $('#jerarquia option:selected').html();

        $('#contrato').val();
        $('#contrato option:selected').html();
        $('#salarioEmpleado').val();
        $('#fechaIngreso').val();*/
    });
}