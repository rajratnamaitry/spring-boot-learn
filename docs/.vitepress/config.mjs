import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "Learn Spring Boot",
  description: "A comprehensive guide to Spring Boot fundamentals, best practices, and practical examples for building enterprise applications.",
  base: '/spring-boot-learn/',
  themeConfig: {
    logo: '/logo.svg',
    // https://vitepress.dev/reference/default-theme-config
    nav: [
      { text: 'Home', link: '/' }
    ],
    sidebar: [
      {
        text: 'Topics',
        items: [
          { text: 'Overview', link: '/pages/overview' },
          { text: 'Rest Controller', link: '/pages/restController' },
          { text: 'Injector Type', link: '/pages/injectorType' },
          { text: 'Hibernate', link: '/pages/hibernate' },
          { text: 'Bean Scope', link: '/pages/beanScope' },
          { text: 'Annotation', link: '/pages/annotation' }
        ]
      }
    ],

    socialLinks: [
      { icon: 'github', link: 'https://github.com/rajratnamaitry/spring-boot-learn' }
    ]
  }
})
