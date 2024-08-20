document.addEventListener("DOMContentLoaded", function() {
    reviewPageLoader.loadDisplayInfo();
});

const reviewPageLoader = {
    loadDisplayInfo : function() {
        let displayInfoId = extractPathVariable.getParameter("id");
        let httpRequest = new XMLHttpRequest();

        httpRequest.onreadystatechange = function() {
            if (httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200) {
                let displayInfoDetail = JSON.parse(httpRequest.responseText);
                let commentsLength = displayInfoDetail.comments.length;

                hideMoreItemButton();

                if (commentsLength > 3) {
                	showMoreItemButton();
                	initMoreItemButton(displayInfoDetail);
                }

                productReviewContainer.initContainer(displayInfoDetail);
            } else if (httpRequest.readyState === XMLHttpRequest.DONE) {
                alert("요청을 처리할 수 없습니다.");
                location.href="/";
                return
            }
        }

        httpRequest.open('GET', "/api/products/" + displayInfoId);
        httpRequest.send();
    }
}

function initMoreItemButton(displayInfoDetail) {
	let moreItemButton = document.querySelector("#more-item-button");
	moreItemButton.addEventListener("click", function() {
		loadProduct(displayInfoDetail);
	});
}

function loadProduct(displayInfoDetail) {
    productReviewContainer.loadMoreReviews(displayInfoDetail);
    hideMoreItemButton();
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

const productReviewContainer = {
    initContainer : function(displayInfoDetail) {
        this.initScoreBox(displayInfoDetail);
        this.initReviewBox(displayInfoDetail);
    },

    initScoreBox(displayInfoDetail) {
        let reviewStar = document.querySelector("#review-score-star");
        let reviewNumerator = document.querySelector("#review-score-numerator");
        let reviewCounter = document.querySelector("#review-count");
        const MAX_SCORE = 5;

        reviewNumerator.innerHTML = displayInfoDetail.averageScore.toFixed(1);
        reviewStar.style.width = displayInfoDetail.averageScore / MAX_SCORE * 100 + "%";
        reviewCounter.innerHTML = displayInfoDetail.comments.length + "건";
    },

    initReviewBox(displayInfoDetail) {
        let reviewListBox = document.querySelector("#review-list-box");
        let reviewTemplate = document.querySelector("#comment-item-template").innerHTML;
        let reviewBindTemplate = Handlebars.compile(reviewTemplate);

        let initialComments = displayInfoDetail.comments.slice(0, 3);
        let reviewData = {
            "displayInfo" : displayInfoDetail.displayInfo,
            "comments" : initialComments
        }

        Handlebars.registerHelper('saveFileName', function(comments) {
            return comments.commentImages.shift().saveFileName;
        });

        Handlebars.registerHelper('floatScore', function(score) {
            return score.toFixed(1);
        });

        Handlebars.registerHelper('formatDate', function(date) {
            return date.split(" ")[0];
        });
        reviewListBox.innerHTML = reviewBindTemplate(reviewData);
    },

    loadMoreReviews(displayInfoDetail) {
        let reviewListBox = document.querySelector("#review-list-box");
        let reviewTemplate = document.querySelector("#comment-item-template").innerHTML;
        let reviewBindTemplate = Handlebars.compile(reviewTemplate);

        let remainingComments = displayInfoDetail.comments.slice(3);
        console.log(remainingComments);
        let reviewData = {
            "displayInfo" : displayInfoDetail.displayInfo,
            "comments" : remainingComments
        }

        let additionalReviews = reviewBindTemplate(reviewData);
        reviewListBox.innerHTML += additionalReviews;
    }
}