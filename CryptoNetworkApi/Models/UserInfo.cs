using System.Collections.Generic;

namespace CryptoNetworkApi.Models
{
    public class UserInfo
    {
        public string Username { get; set; }
        public List<string> Wallets { get; set; }
        public List<string> Subscribes { get; set; }
        public List<string> Subscribers { get; set; }

        public UserInfo(string username)
        {
            Username = username;
            Wallets = new List<string>();
            Subscribes = new List<string>();
            Subscribers = new List<string>();
        }
    }
}
