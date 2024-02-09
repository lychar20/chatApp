
function initFormProfile() {
    const buttons = document.querySelectorAll("[data-hide-show-button]");
    if (buttons) {
        buttons.forEach((button) => {
            const dataAttr = button.getAttribute("data-hide-show-button");
            const container = document.querySelector("[data-hide-show-container='"+dataAttr+"']");
            if (container) {
                button.addEventListener('click', () => {
                    container.classList.toggle('d-none');
                    if (container.classList.contains("review-description")) {
                        button.classList.toggle('d-none');
                        const reviewCard = button.parentElement;
                        const mainReviewCard = reviewCard.parentElement;
                        mainReviewCard.classList.toggle('h-100');
                    }
                });
            }
        });
    }
}

window.addEventListener('load', () => {
    initFormProfile();
});