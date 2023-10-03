package resources

type Audio struct {
	Id    string
	Audio string
}

type Response struct {
	Audio string `json:"Audio"`
}
