# Use Debian 12 (Bookworm) as the base image
FROM debian:bookworm
#FROM debian:bookworm-slim

# Prevent interactive prompts during apt install
ARG DEBIAN_FRONTEND=noninteractive

RUN echo "<?php echo PHP_VERSION . PHP_EOL ?>" > index.php

# Update and install necessary dependencies for adding repositories
RUN apt-get update && apt-get install -y \
    lsb-release \
    ca-certificates \
    apt-transport-https \
    software-properties-common \
    gnupg2 \
    wget \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Add the SURY PHP GPG key and repository
RUN wget -qO - https://packages.sury.org/php/apt.gpg | gpg --dearmor -o /usr/share/keyrings/sury-php.gpg \
    && echo "deb [signed-by=/usr/share/keyrings/sury-php.gpg] https://packages.sury.org/php/ $(lsb_release -sc) main" > /etc/apt/sources.list.d/sury-php.list

# Update package list and install PHP 8.5
RUN apt-get update && apt-get install -y \
    php8.5-cli \
    php8.5-fpm \
    php8.5-mbstring \
    php8.5-xml \
    php8.5-curl \
    && rm -rf /var/lib/apt/lists/*

# Verify installation
RUN php -v

# Set default command
CMD ["php", "-a"]
