// src/composables/useDarkMode.js
import { ref } from 'vue';

export function useDarkMode() {
    const isDark = ref(false);

    const toggleDarkMode = () => {
        isDark.value = !isDark.value;
        document.body.classList.toggle('dark-mode', isDark.value); // 添加或移除 dark-mode 类
    };

    return { isDark, toggleDarkMode };
}
