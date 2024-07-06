const popupNotify = document.getElementById('notify');
const popupTitle = document.getElementById('notify-title');
const popupMessage = document.getElementById('notify-message');
const popupNotifyCloseButton = document.getElementById('notify-close-button');
const loadingAnimation = document.getElementById('popup-loader');
var closeNotifyTimeOut;

function openPopupNotify(title, message, status){
    popupNotify.style.animation = 'appearNotify 0.75s ease forwards';
    popupNotify.className = '';
    popupNotify.classList.add(status);
    popupTitle.innerHTML = title;
    popupMessage.innerHTML = message;

    closeNotifyTimeOut = setTimeout(function(){
        closePopupNotify();
    }, 5000);
}

function closePopupNotify(){
    popupNotify.style.animation = 'hideNotify 0.75s ease forwards';
    
    setTimeout(function(){
		popupNotify.style.animation = '';
		popupNotify.className = '';
        popupTitle.innerHTML = '';
        popupMessage.innerHTML = '';
        
        clearTimeout(closeNotifyTimeOut);
	}, 750);
}

popupNotifyCloseButton.addEventListener('click', function(){
    const iconClose = popupNotifyCloseButton.querySelector('i.fa-xmark');
    iconClose.style.animation = 'closeNotify 0.5s ease forwards';
    closePopupNotify();
    
    setTimeout(function(){
        iconClose.style.animation = '';
    }, 500);
});

function openPopupConfirm(title, message, status, callback){
    popupNotify.style.animation = 'appearNotify 0.75s ease forwards';
    popupNotify.className = '';
    popupNotify.classList.add(status);
    popupNotify.classList.add('option');
    popupTitle.innerHTML = title;
    popupMessage.innerHTML = message;

    const confirmButton = popupNotify.querySelector('#ok');
    const cancelButton = popupNotify.querySelector('#cancel');

    confirmButton.addEventListener('click', function(){
        callback(true);
    });

    cancelButton.addEventListener('click', function(){
        closePopupNotify();
        callback(false);
    })
}

function openLoadingAnimation(){
    loadingAnimation.style.display = 'block';
    
    setTimeout(function(){
		closeLoadingAnimation();
	}, 1500);
}

function closeLoadingAnimation(){
    loadingAnimation.style.display = 'none';
}

/*	<!-- Pop up to show notifications -->
    <div id="notify">
        <div class="d-flex align-items-center">
            <div class="notify-status d-flex justify-content-center align-items-center px-3 px-md-4 fs-1">
                <i class="fa-solid fa-circle-check"></i>
                <i class="fa-solid fa-bug"></i>
                <i class="fa-solid fa-triangle-exclamation"></i>
                <i class="fa-regular fa-comment-dots"></i>
            </div>
    
            <div class="notify-content">
                <div id="notify-title">This is title</div>
                <div id="notify-message">This is a example message.</div>
            </div>
             
            <div id="notify-close-button" class="justify-content-center align-items-center px-2 px-md-3 fs-3">
                <i class="w-75 p-3 text-secondary fa-solid fa-xmark"></i>
            </div>
        </div>

        <div class="w-100 py-3 pe-4 justify-content-end notify-response" id="notify-response">
            <button class="ok me-2 fw-bold" id="ok">Ok</button>
            <button class="cancel fw-bold" id="cancel">Hủy</button>
        </div>
    </div>
    
    <!-- Loading animation -->
    <div id="popup-loader">
    	<div class="circle"></div>
    </div>
*/