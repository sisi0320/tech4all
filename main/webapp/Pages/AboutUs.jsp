<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - Tech4All</title>
    <link rel="stylesheet" href="/Tech4All/Stylesheet/aboutus.css"/>
    <script src="https://kit.fontawesome.com/cfef85a76c.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="Header.jsp"/>
    <main>
        <section class="about">
            <h1>About Us</h1>
            <h2>Who We Are</h2>
            <div class="about-content">
                <img src="/Tech4All/Images/T.png" alt="Tech4All Store" class="about-img">
                <div class="about-text">
                    <p>Welcome to Tech4All, your premier destination for the latest and greatest mobile gadgets!</p>
                    <p>At Tech4All, we strive to bring you the best mobile devices at unbeatable prices.</p>
                    <p>Founded in 2024, Tech4All aims to revolutionize the mobile tech industry by providing quality and affordable gadgets to everyone.</p>
                    <p>We believe in making technology accessible to everyone. Our dedicated team constantly searches for new and innovative products to meet our customers' needs.</p>
                </div>
            </div>
        </section>

        <section class="values">
            <h2>Our Values</h2>
            <div class="values-content">
                <div class="value-item">
                    <h3>Innovation</h3>
                    <p>We always strive to bring you the most innovative gadgets on the market.</p>
                </div>
                <div class="value-item">
                    <h3>Customer Satisfaction</h3>
                    <p>Your satisfaction is our top priority. We are committed to providing exceptional customer service.</p>
                </div>
                <div class="value-item">
                    <h3>Quality</h3>
                    <p>We provide high-quality gadgets that are both reliable and affordable.</p>
                </div>
                <div class="value-item">
                    <h3>Integrity</h3>
                    <p>Our business practices are rooted in honesty and transparency.</p>
                </div>
            </div>
        </section>

        <section class="contact">
            <h2>Contact Us</h2>
            <p>Have questions or need assistance? Reach out to us!</p>
            <ul>
                <li><span class="icon">📞</span> Phone: <a href="tel:+1234567890">+977 (9841) 567-890</a></li>
                <li><span class="icon">📞</span> Phone: <a href="tel:+0987654321">+977 (9870) 654-321</a></li>
                <li><span class="icon">📧</span> Email: <a href="mailto:support@tech4all.com">support@tech4all.com</a></li>
            </ul>
            <form id="contact-form">
                <label for="name">Your Name:</label>
                <div class="input-icon">
                    <span class="icon">👤</span>
                    <input type="text" id="name" name="name" placeholder="your name" required>
                </div>

                <label for="email">Your Email:</label>
                <div class="input-icon">
                    <span class="icon">📧</span>
                    <input type="email" id="email" name="email" placeholder="youremail@example.com" required>
                </div>

                <label for="message">Your Message:</label>
                <textarea id="message" name="message" rows="5" placeholder="Your message here..." required></textarea>

                <button type="submit">Send Message</button>
            </form>
        </section>
    </main>

    <footer>
        <p>&copy; 2024 Tech4All. All rights reserved.</p>
    </footer>

    <div id="modal" class="modal">
        <div class="modal-content">
            <span id="close" class="close">&times;</span>
            <h3>Thank You!</h3>
            <p>Your message has been sent successfully. We'll get back to you soon!</p>
        </div>
    </div>

    <script>
    document.getElementById('contact-form').addEventListener('submit', function(e) {
        e.preventDefault();

        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const message = document.getElementById('message').value;

        if (name && email && message) {
            showModal();
            this.reset();
        } else {
            alert('Please fill out all fields before submitting.');
        }
    });

    function showModal() {
        const modal = document.getElementById('modal');
        modal.style.display = 'block';

        const closeBtn = document.getElementById('close');
        closeBtn.onclick = function() {
            modal.style.display = 'none';
        };

        window.onclick = function(event) {
            if (event.target === modal) {
                modal.style.display = 'none';
            }
        };
    }
    </script>
</body>
</html>
