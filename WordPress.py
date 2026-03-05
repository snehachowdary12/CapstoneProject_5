import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

# ---------------- Fixture ----------------
@pytest.fixture
def driver():
    options = webdriver.ChromeOptions()
    options.add_argument("--start-maximized")  # start browser maximized
    driver = webdriver.Chrome(options=options)
    yield driver
    driver.quit()  # closes the browser after the test

# ---------------- Combined Test ----------------
def test_wordpress_themes_navigation_and_search(driver):
    wait = WebDriverWait(driver, 20)

    # Step 1: Open WordPress homepage
    driver.get("https://wordpress.org/")
    wait.until(EC.title_contains("WordPress"))
    assert "WordPress" in driver.title

    # Step 2: Hover over menu and click Themes
    extend_menu = wait.until(
        EC.visibility_of_element_located((By.XPATH, '//*[@id="modal-1-content"]/ul/li[4]/button/span'))
    )
    ActionChains(driver).move_to_element(extend_menu).perform()

    themes_link = wait.until(
        EC.element_to_be_clickable((By.LINK_TEXT, "Themes"))
    )
    themes_link.click()

    wait.until(EC.title_contains("Theme Directory"))
    assert "Theme Directory" in driver.title

    # Step 3: Search for "Minimal"
    search_box = wait.until(
        EC.visibility_of_element_located((By.ID, "wp-block-search__input-8"))
    )
    search_box.clear()
    search_box.send_keys("Forest")
    search_box.send_keys(Keys.ENTER)

    # Step 4: Verify theme titles are present
    wait.until(EC.presence_of_all_elements_located((By.XPATH, "//h2[contains(@class,'wp-block-post-title')]")))
    themes = driver.find_elements(By.XPATH, "//h2[contains(@class,'wp-block-post-title')]")

    assert len(themes) > 0