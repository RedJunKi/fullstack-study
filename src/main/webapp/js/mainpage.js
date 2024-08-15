document.addEventListener("DOMContentLoaded", function() {
	initViewReservationButton();
	loadPromotionList(getPromotionList);
	initDefaultProductList();
	initTab();
	initMoreItemButton();
});

function initViewReservationButton() {
	let viewReservationButton = document.querySelector("#check-reservation-by-email");
	if (viewReservationButton) {
		viewReservationButton.addEventListener("click", function() {
			location.href= "/my-reservation";
		})
		return;
	}
	viewReservationButton = document.querySelector("#check-reservation-without-email");
	viewReservationButton.addEventListener("click", function () {
		location.href= "/booking-login";
	})
}

function getPromotionList() {
	startPromotionSlider();
}

function loadPromotionList(getPromotionList) {
	let httpRequest = new XMLHttpRequest();
	let promotionHtmlTemplate = document.querySelector("#promotion-template-container").innerHTML;
	let promotionSlider = document.querySelector("#promotion-slider");

	httpRequest.onload = function() {
	    if (httpRequest.status === 200) {
            let response = JSON.parse(httpRequest.responseText);
            let promotionList = response.items;
            promotionList.forEach(products => {
                let promotionItem = promotionHtmlTemplate.replace("{productImageUrl}", products.productImageUrl);
                promotionSlider.innerHTML += promotionItem;
            });

            let promotionItem = promotionHtmlTemplate.replace("{productImageUrl}", promotionList[0].productImageUrl);
            promotionSlider.innerHTML += promotionItem;

		    getPromotionList();
	    } else {
	        alert('서버와의 연결이 되지 않습니다');
	    }

	}

	httpRequest.open("GET", "/api/promotions");
	httpRequest.send();
}

function startPromotionSlider() {
	let promotionSlider = document.querySelector("#promotion-slider");
	let currentIndex = 0;
	let width = document.getElementById('promotion-slider').offsetWidth;
	let promotionListLength = document.querySelectorAll(".promotion_item").length;

	setInterval(function() {
		promotionSlider.style.transition = "0.3s";
		promotionSlider.style.transform = "translate(-" + (width * currentIndex) + "px, 0px)";
		currentIndex += 1;

		if(currentIndex === promotionListLength) {
			setTimeout(function(){
            	promotionSlider.style.transition = '0s';
                promotionSlider.style.transform = "translate(0px, 0px)";
            },300)
            currentIndex = 0;
		}

	}, 2000)
}

function initTab() {
	document.querySelectorAll(".category_tab").forEach(tab => {
		tab.addEventListener("click", event => {

			document.querySelectorAll(".category_tab_anchor").forEach(tab => {
				tab.classList.remove("active");
			})
			event.currentTarget.children[0].className += " active";

			changeTab();
		});
	});
}

function changeTab() {
	initProductContainer();
	hideMoreItemButton()
	loadProduct();
	showMoreItemButton();
}

function changeCategoryCount(currentTabCount) {
	let categoryCountNode = document.querySelector("#category-item-count");
	categoryCountNode.innerText = currentTabCount + "개";
}

function initMoreItemButton() {
	let moreItemButton = document.querySelector("#more-item-button");
	moreItemButton.addEventListener("click", function() {
		loadProduct();
	})
}

function loadProduct() {
	let currentCategoryId = getCurrentCategoryId();
	let currentItemCount = document.querySelectorAll('.product_item').length;

	let httpRequest = new XMLHttpRequest();
	let productLeftContainer = document.querySelector(".lst_event_box_left");
	let productRightContainer = document.querySelector(".lst_event_box_right");
	let productHtmlTemplate = document.querySelector("#item-list").innerHTML;

	httpRequest.onload = function() {
	    if (httpRequest.status === 200) {
		    let addedProductList = JSON.parse(httpRequest.responseText);
		    hideMoreItemButton();

		    if (currentItemCount + addedProductList.products.length < addedProductList.totalCount) {
		    	showMoreItemButton();
		    }


		    for (let pi = 0; pi < addedProductList.products.length; pi++) {
		    	let addedProduct = productHtmlTemplate.slice()
                        .replace("{id}", addedProductList.products[pi].displayInfoId)
                        .replace("{description}", addedProductList.products[pi].productDescription)
                        .replace("{altDescription}", addedProductList.products[pi].productDescription)
                        .replace("{placeName}", addedProductList.products[pi].placeName)
                        .replace("{productImageUrl}", addedProductList.products[pi].productImageUrl)
                        .replace("{content}", addedProductList.products[pi].productContent);

		    	if (pi % 2 == 0) {
		    		productLeftContainer.innerHTML += addedProduct;
		    	}else {
		    		productRightContainer.innerHTML += addedProduct;
		    	}
		    }
		    changeCategoryCount(addedProductList.totalCount);
	    } else {
	        alert('서버와의 연결이 되지 않습니다');
	    }
	}


	httpRequest.open("GET", "/api/products?categoryId=" + currentCategoryId + "&start=" + currentItemCount);
	httpRequest.send();
}

function getCurrentCategoryId() {
	let tabItem = document.querySelector(".active");
	return tabItem.parentElement.getAttribute("data-category");
}

function initProductContainer() {
	let productLeftContainer = document.querySelector(".lst_event_box_left");
	let productRightContainer = document.querySelector(".lst_event_box_right");

	productLeftContainer.innerHTML = '';
	productRightContainer.innerHTML = '';

}

function showMoreItemButton() {
	let moreItemButton = document.querySelector("#more-item-button");
	if (moreItemButton.style.display === "none") {
		moreItemButton.style.display = "";
	}
}

function hideMoreItemButton() {
	let moreItemButton = document.querySelector("#more-item-button");
	moreItemButton.style.display = "none";
}

function initDefaultProductList() {
	initProductContainer();
	loadProduct();
}