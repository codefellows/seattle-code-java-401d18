# Spring Security Cheat Sheet

## Step 1: set up a user model and repo

## Step 2: create a controller for that model

## Step 3: UserDetailsServiceImpl implements UserDetailsService

Gets a User from the database by username (make sure your repository has the method to make this easy!)

## Step 4: ApplicationUser implements UserDetails

Use IntelliJ to implement the methods; make the boolean ones all return true.

## Step 5: WebSecurityConfig extends WebSecurityConfigurerAdapter

- Has a UserDetailsService
- passwordEncoder bean
- Configure AuthenticationManagerBuilder
- Configure HttpSecurity
  - CORS? CSRF?
  - Matchers for URLs that are allowed
    - Ensure that login and signup URLs allowed; also consider homepage etc.
  - Form login with login page set up
  - Logout

## Step 6: registration page
- Create it w/ form
- Ensure it POSTs to a route your controller is ready for
- Check it's saving in the DB

## Step 7: login page
- Create it w/ form
- Ensure it posts to the route you specified in WebSecurityConfig
- Try it out!
- Add to a template w/ things about the Principal

## Step 8: home page
- Needs a logout button
- Should have a link to the route you specified in WebSecurityConfig
