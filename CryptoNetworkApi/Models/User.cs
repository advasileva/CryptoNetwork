using System;

namespace CryptoNetworkApi.Models
{
    public class User
    {
        private string _id;
        public string Id => _id;

        private string _login;
        public string Login
        {
            get => _login;
            set
            {
                if (!IsCorrectLogin(value))
                {
                    throw new ArgumentException("Incorrect login");
                }
                _login = value;
            }
        }

        private string _password;
        public string Password
        {
            get => _password;
            set
            {
                if (!IsCorrectPassword(value))
                {
                    throw new ArgumentException("Incorrect password");
                }
                _password = value;
            }
        }

        public User(string login, string password)
        {
            _id = GenerateGuid();
            Login = login;
            Password = password;
        }

        private string GenerateGuid() => "guid";

        private bool IsCorrectLogin(string login) => true;

        private bool IsCorrectPassword(string password) => true;
    }
}
