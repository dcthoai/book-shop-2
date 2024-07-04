
const searchButton = document.getElementById('search-button');
const inputSearch = document.getElementById('search-input');
const bookResultsContainer = document.getElementById('book-results');
const totalResults = document.getElementById('total-results');
const sortbyOptions = document.getElementById('sort-by-options');

function getDataBook(book){
    const html = `<div class="col-lg-3 col-md-4 col-6 mb-4">
                    <div class="product">
                        <a href="${BASE_URL}/product?id=${ book.id }" class="product__link">
                            <div class="product__img"><img src="${BASE_URL}/${ book.thumbnailPath }" alt="product image"></div>

                            <div class="product__info p-3 pt-2">
                                <h6 class="name mb-2">${ book.title }</h6>

                                <div class="rating mb-2">
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                    <i class="fa-regular fa-star"></i>
                                </div>
                                
                                <div>Đã bán: <span>${ book.purchases }</span></div>

                                <div class="price">
                                    ${ book.sellPrice } đ
                                    <span class="discount">-${ book.discount }%</span>
                                </div>

                                <del class="cost">${ book.cost }đ</del>
                            </div>
                        </a>
                    </div>
                </div>`;

    return html;
}

function searchBookByKeyword(){
    let keyword = inputSearch.value.trim();

    if (keyword){
        fetch(BASE_URL + `/shop/search?name=${keyword}`)
        .then(response => response.json())
        .then(data => {
            let resultsHtml = 'Không tìm thấy sản phẩm nào.';

            if (data) {
                if (Array.isArray(data)) {
                    if (data.length > 0) {
                        resultsHtml = '';

                        data.forEach(book => {
                            resultsHtml += getDataBook(book);
                        });

                        totalResults.innerHTML = data.length;
                    }
                }
            }

            bookResultsContainer.innerHTML = resultsHtml;
        })
        .catch(error => {
            // openPopupNotify('Không thể tìm kiếm', 'Đã có lỗi xảy ra, vui lòng thử lại sau', 'error');
            console.error(error);
        })
    }
}

// Search when click button
searchButton.addEventListener('click', function(){
    searchBookByKeyword();
});

// Search when enter
inputSearch.addEventListener('keydown', function(event){
    if (event.key === 'Enter' || event.code === 13) {
        searchBookByKeyword();
    }
});

sortbyOptions.addEventListener('change', function(){
    const option = sortbyOptions.value;

    fetch(BASE_URL + `/shop/search/sort?by=${option}`)
    .then(response => response.json())
    .then(data => {
        let resultsHtml = 'Không tìm thấy sản phẩm nào';

        if (data) {
            if (Array.isArray(data)) {
                if (data.length > 0) {
                    resultsHtml = '';

                    data.forEach(book => {
                        resultsHtml += getDataBook(book);
                    });

                    totalResults.innerHTML = data.length;
                }
            }
        }

        bookResultsContainer.innerHTML = resultsHtml;
    })
    .catch(error => {
        // openPopupNotify('Không thể tìm kiếm', 'Đã có lỗi xảy ra, vui lòng thử lại sau', 'error');
        console.error(error);
    })
})

document.addEventListener('DOMContentLoaded', function(){
    fetch(BASE_URL + `/shop/get`)
    .then(response => response.json())
    .then(data => {
        let resultsHtml = 'Không tìm thấy sản phẩm nào';

        if (data) {
            if (Array.isArray(data)) {
                if (data.length > 0) {
                    resultsHtml = '';

                    data.forEach(book => {
                        resultsHtml += getDataBook(book);
                    });

                    totalResults.innerHTML = data.length;
                }
            }
        }

        bookResultsContainer.innerHTML = resultsHtml;
    })
    .catch(error => {
        // openPopupNotify('Không thể tìm kiếm', 'Đã có lỗi xảy ra, vui lòng thử lại sau', 'error');
        console.error(error);
    })
});


const filterCategoryItems = document.querySelectorAll('.filter-category-item');
const filterLanguageItems = document.querySelectorAll('.filter-language-item');
const filterStarsItems = document.querySelectorAll('.filter-stars-item');
const filterPriceBtn = document.getElementById('filter-btn-price');
const searchModel = {
	categoryId: 0,
	languageId: 0,
	stars: 0,
	minPrice: 0,
	maxPrice: 0
};

filterCategoryItems.forEach(button => {
	button.addEventListener('click', () => {
		let categoryId = button.getAttribute('data-id');
		searchModel.categoryId = parseInt(categoryId);
		
		filterBookBySearchModel();
	});
});

filterLanguageItems.forEach(button => {
	button.addEventListener('click', () => {
		let languageId = button.getAttribute('data-id');
		searchModel.languageId = parseInt(languageId);
		
		filterBookBySearchModel();
	});
});

filterStarsItems.forEach(button => {
	button.addEventListener('click', () => {
		let stars = button.getAttribute('data-star');
		searchModel.stars = parseInt(stars);
		
		filterBookBySearchModel();
	});
});

filterPriceBtn.addEventListener('click', () => {
	const minPrice = document.getElementById('min-price').value;
    const maxPrice = document.getElementById('max-price').value;
    
    if (parseInt(minPrice) > 0)
	    searchModel.minPrice = parseInt(minPrice);
	else 
		searchModel.minPrice = 0
	if (parseInt(maxPrice) > 0)
    	searchModel.maxPrice = parseInt(maxPrice);
    else 
    	searchModel.maxPrice = -1;
    	
    filterBookBySearchModel();
});

function filterBookBySearchModel() {
	fetch(BASE_URL + '/shop/filter', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(searchModel)
	})
	.then(response => response.json())
	.then(data => {
        let resultsHtml = 'Không tìm thấy sản phẩm nào';

        if (data) {
            if (Array.isArray(data)) {
                if (data.length > 0) {
                    resultsHtml = '';

                    data.forEach(book => {
                        resultsHtml += getDataBook(book);
                    });

                    totalResults.innerHTML = data.length;
                }
            }
        }

        bookResultsContainer.innerHTML = resultsHtml;
    })
	.catch(error => {
        bookResultsContainer.innerHTML = 'Không tìm thấy sản phẩm nào';
		console.error(error);
	})
}
