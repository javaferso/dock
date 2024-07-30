
<div class="text-white card bg-secondary bg-gradient mt-3 z-3" style="--bs-bg-opacity: .3; width: auto; height: 300px;">
    <footer class="footer d-flex flex-wrap justify-content-between align-items-center py-6 my-auto">
        <div class="logo-img col-md-2">
            <img src="logos/smu_logo.svg" class="img-fluid rounded-start" alt="logo SMU"/>
        </div>
        <div class="col-md-4 d-flex align-items-center">
            <a href="/" class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">
            </a>
            <ul class="list-unstyled text-muted small">
               <li>Gerencia TI Operaciones y Formatos</li>
               <li>&copy; <%= new java.util.Date().getYear() + 1900 %> SMU S.A.</li>
            </ul>
        </div>
        
        <!-- Changelog Section -->
        <div class="col-md-4 mt-3">
            <h6 class="text-muted">Changelog</h6>
            <dl class="row text-gray-400">
                <dt class="col-sm-3"><i class="fas fa-v x0.4"></i> [2.3.5]</dt>
                <dd class="col-sm-9">2024-07-12</dd>
                
                <dt class="col-sm-3"><i class="fas fa-plus-circle x0.4"></i> Added: </dt>
                <dd class="col-sm-9">Nueva Pagina de Gestion Servicios</dd>
                
                <dt class="col-sm-3"><i class="fas fa-exchange-alt x0.4"></i>Changed: </dt>
                <dd class="col-sm-9"> Mejor disposicion barra de navegacion</dd>
                
                <dt class="col-sm-3"><i class="fas fa-wrench x0.4"></i><u> Fixed:</u></dt>
                <dd class="col-sm-9"> 
                    <p>- Envio ping al servidor y enlace al cargar pag.</p>
                    <p>- Color verde-rojo estado online-offline en serv.</p>
                </dd>
               
            </dl>
        </div>
        <!-- End of Changelog Section -->
    </footer>
</div>