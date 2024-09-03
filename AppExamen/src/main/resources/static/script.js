document.addEventListener('DOMContentLoaded', function() {
    const authButton = document.getElementById('auth-button');
    const viewCartButton = document.getElementById('view-cart');
    const mainContent = document.getElementById('main-content');

    checkLoginStatus();

    authButton.addEventListener('click', () => {

        window.location.href = 'login.html';
    });
    viewCartButton.addEventListener('click', () => {
        window.location.href = 'cart.html';
    });
    function checkLoginStatus() {
        fetch('/api/v1/user/check')
            .then(response => response.json())
            .then(data => {
                data.loggedIn = true;
                if (data.loggedIn) {
                    loadProductList();  // Load products if logged in
                    authButton.textContent = 'User Page';
                    viewCartButton.classList.remove('hidden');
                } else {
                    alert('Please log in to view products.');
                    window.location.href = 'login.html';  // Redirect to login if not logged in
                }
            })
            .catch(error => {
                console.error('Error checking login status:', error);
            });
    }
    function loadProductList() {
        mainContent.innerHTML = '<h2>Available Products</h2><div id="product-list"></div>';
        fetch('/api/v1/products')
            .then(response => response.json())
            .then(products => {
                const productList = document.getElementById('product-list');
                products.forEach(product => {
                    const productDiv = document.createElement('div');
                    productDiv.innerHTML = `
                        <h3>${product.name}</h3>
                        <p>Price: $${product.price}</p>
                        <p>${product.description}</p>
                        <button class="add-to-cart-button" data-product-id="${product.id}">Add to Cart</button>
                    `;
                    productList.appendChild(productDiv);
                });
                document.querySelectorAll('.add-to-cart-button').forEach(button => {
                    button.addEventListener('click', () => {
                        const productId = button.dataset.productId;
                        addToCart(productId);
                    });
                });
            })
            .catch(error => {
                console.error('Error loading products:', error);
            });
    }
    document.addEventListener('DOMContentLoaded', () => {
        document.querySelectorAll('.add-to-cart').forEach(button => {
            button.addEventListener('click', () => {
                const productId = button.dataset.productId;
                addToCart(productId);
            });
        });
    });

    function addToCart(productId) {
        fetch('http://localhost:8080/api/v1/basket/add-to-cart', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                itemId: productId
            })
        })
            .then(response => {
                if (response.ok) {
                    alert('Product added to cart successfully!');
                } else {
                    return response.json().then(data => {
                        throw new Error(data.message || 'Failed to add product to cart');
                    });
                }
            })
            .catch(error => {
                console.error('Error adding product to cart:', error);
                alert('Error adding product to cart: ' + error.message);
            });
    }


});
